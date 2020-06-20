package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import org.example.model.Role;
import org.example.typehandler.SexEnumTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * spring 注解配置 mybatis
 * @author chenj
 */
@Configuration
public class MybatisConfig implements EnvironmentAware {

       private Logger logger = Logger.getLogger(MybatisConfig.class);

       private  Environment env;

    /**
     *  配置数据库连接池
      * @return Druid 类型数据库连接池
     */
    @Bean
        public DataSource dataSource() throws SQLException {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.jdbc.datasource.driver"));
            dataSource.setUrl(env.getProperty("spring.jdbc.datasource.url"));
            dataSource.setUsername(env.getProperty("spring.jdbc.datasource.username"));
            dataSource.setPassword(env.getProperty("spring.jdbc.datasource.password"));

            dataSource.setInitialSize(Integer.parseInt(
                    (env.getProperty("spring.datasource.druid.initialSize","5"))));
            dataSource.setMaxActive(Integer.parseInt(
                    (env.getProperty("spring.datasource.druid.maxActive","10"))));
            dataSource.setMaxWait(Integer.parseInt(
                    (env.getProperty("spring.datasource.druid.maxWait","3000"))));


            return  dataSource;
        }

    /**
     *  配置 SqlSessionFactory
     * @return myBaits 的核心工厂
     */
    @Bean("sqlSessionFactory")
        public SqlSessionFactoryBean  sqlSessionFactoryBean() throws SQLException {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setTypeAliases(new Class[]{Role.class});
            factoryBean.setTypeHandlers(new TypeHandler[]{new SexEnumTypeHandler()});
            factoryBean.setDataSource(dataSource());
            ResourcePatternResolver  resourceResolver = new PathMatchingResourcePatternResolver();
            String mapperLocations = env.getProperty("mybatis.mapper-Locations");
            if(mapperLocations !=null){
                try{
                   Resource[] mappers= resourceResolver.getResources(mapperLocations);
                   factoryBean.setMapperLocations(mappers);
                }catch (IOException e){
                    //ignore
                }
            }


            return  factoryBean;
        }


    /**
     * Mapper 接口自动扫描器
     * @return Mapper扫描器
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
           MapperScannerConfigurer scanner = new MapperScannerConfigurer();
           scanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
           scanner.setBasePackage("org.example.*");
           scanner.setAnnotationClass(Mapper.class);
           return  scanner;
     }



    @Override
    public void setEnvironment(Environment environment) {
        this.env =environment;
    }
}
