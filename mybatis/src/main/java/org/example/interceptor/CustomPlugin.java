package org.example.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Properties;


/**
 * 实现自定义MyBatis插件的demo
 * @author chenj
 */
@Intercepts({
        @Signature(
          type= StatementHandler.class, //需要拦截的类
          method = "prepare",    //需要拦截的方法签名和方法参数
           args = {Connection.class,Integer.class})})
public class CustomPlugin  implements Interceptor {

    private Logger logger = Logger.getLogger(CustomPlugin.class);
    private Properties properties = null;

    /**
     *  插件方法，它将代替 StatementHandler 的 prepare方法
     * @param invocation 被代理的原对象
     * @return  返回预编译后的 PreparedStatementHandler
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
          StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
          //进行绑定
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        Object object = null;
        /*
         *  分离代理类对象(由于目标类可能被多个拦截器[插件]拦截，从而形成多次代理，通过循环可以分离出原始的目标类)
         *  h应该是 invocationHandler
         */
        while(metaStatementHandler.hasGetter("h")){
            object = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(object);
        }
        statementHandler  =(StatementHandler) object;
        String sql =
                (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        Long parameterObject =
                (Long) metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        logger.info("执行的SQL:【" + sql+"】");
        logger.info("参数:【" + parameterObject +"】");
        logger.info("before...");
        //如果当前代理对象代理的是一个非代理对象，那么他就会回调真实对象的方法
        //如果不是，那么它就会调度下个插件代理对象的 invoke 方法
        Object  obj = invocation.proceed();
        logger.info("after...");
        return  obj;
    }


    /**
     *  生成代理对象
     * @param target 被拦截的对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        //采用系统默认的Plugin.wrap方法生成
        return Plugin.wrap(target,this);
    }

    /**
     *  设置参数，MyBatis 初始化时，就会生成插件实例，并且调用这个方法
     * @param properties 配置参数
     */
    @Override
    public void setProperties(Properties properties) {
             this.properties = properties;
             logger.info(properties);
    }
}
