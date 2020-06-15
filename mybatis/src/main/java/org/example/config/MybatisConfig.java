package org.example.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.example.mapper.RoleMapper;
import org.example.model.Role;


/**
 *  代码方式实现mybatis注解
 *  @author chenj
 */
public class MybatisConfig {

    private static final  Class<MybatisConfig> LOCK = MybatisConfig.class;
    private static SqlSessionFactory  sqlSessionFactory = null;

    private  MybatisConfig(){}

    public static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            if(sqlSessionFactory !=null){
                 return  sqlSessionFactory;
            }
            //数据库连接池的信息
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setUrl("jdbc:mysql://localhost:3306/backend");
            dataSource.setDefaultAutoCommit(false);

            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development",transactionFactory,dataSource) ;

            Configuration configuration = new Configuration(environment);
            configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
            configuration.addMapper(RoleMapper.class);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

            return  sqlSessionFactory;


        }
    }

}
