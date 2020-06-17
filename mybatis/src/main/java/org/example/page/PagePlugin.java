package org.example.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * @author chenj
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args={Connection.class,Integer.class}
)})
public class PagePlugin implements Interceptor {
    /**
     * 插件的默认参数，可配置默认值
     */
    private Integer defaultPage ;
    private Integer defaultPageSize;
    private Boolean defaultUseFlag;
    private Boolean defaultCheckFlag;
    private Boolean defaultCleanOrderBy;



    @Override
    public Object intercept(Invocation invocation) throws Throwable {
         StatementHandler statementHandler = (StatementHandler) getUnProxyObject(invocation.getTarget());
         MetaObject  metaStatementHandler = SystemMetaObject.forObject(statementHandler);
         String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        MappedStatement ms = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        //不是select 语句
        if(!checkSelect(sql)){
            //执行原方法
            return  invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object  parameterObject = boundSql.getParameterObject();
        PageParams pageParams = getPageParamsForParamObj(parameterObject);
        if(pageParams == null){
            //无法获取分页参数,不进行分页
            return   invocation.proceed();
        }
        //配置中是否启用分页功能
        Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
        if(!useFlag){
             return  invocation.proceed();
        }
        //获取相关的参数配置
        Integer currentPage = pageParams.getCurrentPage() == null ? this.defaultPage :pageParams.getCurrentPage();
        Integer pageSize = pageParams.getPageSize() ==null? this.defaultPageSize :pageParams.getPageSize();
        Boolean checkFlag = pageParams.getCheckFlag()==null ? this.defaultCheckFlag : pageParams.getCheckFlag();
        Boolean cleanOrderFlag = pageParams.getCleanOrderBy()==null ?this.defaultCleanOrderBy:pageParams.getCleanOrderBy();

        //计算总条数
        int total = getTotal(invocation,metaStatementHandler,boundSql,cleanOrderFlag);
        //回填总条数
        pageParams.setTotal(total);
        //计算总页数
        int totalPage = total % pageSize == 0 ? total/pageSize : total/pageSize+1;
        //回填总页数
        pageParams.setTotalPage(totalPage);
        //检查当前页码的有效性
        checkPage(checkFlag,currentPage,totalPage);
        //修改sql
        return preparedSQL(invocation,metaStatementHandler,boundSql,currentPage,pageSize);

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    /**
     *  设置插件配置参数
     * @param properties 配置参数
     */
    @Override
    public void setProperties(Properties properties) {
         //从配置中获取参数
         String  strDefaultPage = properties.getProperty("default.page","1");
         String strDefaultPageSize = properties.getProperty("default.pageSize","50");
         String  strDefaultUseFlag =properties.getProperty("default.useFlag","false");
         String strDefaultCheckFlag =properties.getProperty("default.checkFlag","false");
         String  strDefaultCleanOrderBy= properties.getProperty("default.cleanOrderBy","false");
         //设置默认参数
         this.defaultPage = Integer.parseInt(strDefaultPage);
         this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
         this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
         this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
         this.defaultCleanOrderBy = Boolean.parseBoolean(strDefaultCleanOrderBy);
    }

    /**
     * 从代理对象中分离出真实对象
     * @param target 需要分离的代理对象
     * @return 非代理的 StatementHandler对象
     */
    private Object getUnProxyObject(Object target){
        MetaObject metaStatementHandler = SystemMetaObject.forObject(target);

        Object object = null;
        //不断分离代理对象知道没有invocationHandler
        while(metaStatementHandler.hasGetter("h")){
             object = metaStatementHandler.getValue("h");
             metaStatementHandler = SystemMetaObject.forObject(object);
        }
        if(object == null){
            return  target;
        }
        return  object;
    }

    /**
     *  判断是否是 select 语句
     * @param sql --当前执行的SQL
     * @return 是否是 select 语句
     */
    private  boolean checkSelect(String sql){
         String trimSql = sql.trim();
         int index = trimSql.toLowerCase().indexOf("select");
         return  index ==0;
    }

    /**
     *   分离出分页参数
     *  @return 分页参数
     */
    public  PageParams getPageParamsForParamObj(Object parameterObject) throws Exception {

        if(parameterObject == null){
            return null;
        }
        //处理map参数 ,多个匿名参数和@Param参数，都是map
        if(parameterObject instanceof Map){
            @SuppressWarnings("unchecked")
            Map<String,Object> paramMap = (Map<String, Object>) parameterObject;
            Set<String> keySet = paramMap.keySet();
            for (String key : keySet) {
                Object value = paramMap.get(key);
                if (value instanceof PageParams) {
                    return (PageParams) value;
                }
            }
        }else if( parameterObject instanceof PageParams){
            //如果是继承与分页参数
            return (PageParams) parameterObject;
        }else {

            //从POJO 属相中读取分页参数
            Field[] fields = parameterObject.getClass().getDeclaredFields();
            for(Field field :fields){
                if(field.getType() == PageParams.class){
                    PropertyDescriptor pd =
                            new PropertyDescriptor(field.getName(),parameterObject.getClass());
                    Method method =pd.getReadMethod();
                    return (PageParams) method.invoke(parameterObject);
                }
            }
        }
        return null;
    }


    /**
     *  获取总条数
     * @param invocation 入参
     * @param metaObject  statementHandler
     * @param boundSql sql
     * @param cleanOrderBy 是否清除order by 语句
     * @return SQL 查询总数
     * @throws Throwable 异常
     */
    private int getTotal(Invocation invocation ,MetaObject metaObject ,
                         BoundSql boundSql ,Boolean cleanOrderBy) throws Throwable{
        //获取当前的 mappedStatement
        MappedStatement ms  = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //配置对象
       Configuration cfg = ms.getConfiguration();
        //获取当前需要执行的SQL
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        //去掉最后的 order by语句
        if(cleanOrderBy){
            sql = this.cleanOrderByForSql(sql);
        }
        //改为统计总数的SQL
        String countSql = "select count(*) as total from(" +sql+") $_paging";
        //获取拦截方法参数
        Connection connection = (Connection) invocation.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0;
        try{
            //预编译统计总数SQL
            ps = connection.prepareStatement(countSql);
            //构建总计数BoundSql
            BoundSql countBoundSql = new BoundSql(cfg, countSql ,
                    boundSql.getParameterMappings(),boundSql.getParameterObject());
            //构建MyBatis 的ParameterHandler 来不来设置总数的SQL参数
            ParameterHandler  handler =  new DefaultParameterHandler(ms,boundSql.getParameterObject(),countBoundSql);
            //设置总数SQL 语句
            handler.setParameters(ps);
            //执行查询
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                total= rs.getInt("total");
            }
    } finally {
      // 这里不能关闭Connection，不然后续的SQL没法继续
      if (ps != null) {
        ps.close();
      }
      }


     return  total;
    }

    /**
     *  清除 order by 语句
     */
    private  String cleanOrderByForSql(String sql){
        StringBuilder  sb = new StringBuilder(sql);
        String newSql = sql.toLowerCase();
        //如果没有 order 语句直接返回
        if(!newSql.contains("order")){
            return  sql;
        }
        int index = newSql.lastIndexOf("order");
        return  sb.substring(0,index).toString();

    }


    /**
     *  检查当前页码的有效性
     */
    private  void checkPage(Boolean checkFlag ,Integer pageNum ,Integer pageTotal) throws Exception {
         if(checkFlag){
              //检查页码是否合法
             if(pageNum > pageTotal){
                 throw new Exception("查询失败,查询页码【" + pageNum+"】大于总页数" );
             }
         }

    }


    /**
     *  预编译改写后的SQL,并设置分页参数
     */
    private Object preparedSQL(Invocation invocation ,MetaObject metaObject ,BoundSql boundSql ,
                              int currentPage ,int pageSize) throws Exception {
        //获取当前需要执行的SQL
        String sql = boundSql.getSql();
        String newSql = "select * from (" +sql +") $_paging_table limit ?,?";
        //修改当前需要执行的SQL
        metaObject.setValue("delegate.boundSql.sql",newSql);
        //执行编译,相当于 StatementHandler 执行了 prepared() 方法 ,这个时候，就剩下两个分页参数没有设置
        Object statementObj = invocation.proceed();
        //设置两个分页参数
        this.preparePageDataParams((PreparedStatement) statementObj,currentPage,pageSize);

        return  statementObj;

    }

    /**
     *  设置分页参数
     */
    private void   preparePageDataParams(PreparedStatement ps ,int currentPage ,int pageSize) throws SQLException {
         //由于参数是最后两个，容易得到其位置
        int index = ps.getParameterMetaData().getParameterCount();
         //设置两个分页参数
         ps.setInt(index -1,(currentPage -1)* pageSize);
         ps.setInt(index,pageSize);
    }






}
