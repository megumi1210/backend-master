package org.example.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Spring Ioc 的基本容器配置
 * @author chenj
 */
@Configuration
@ComponentScan("org.example.*")
@Import(MybatisConfig.class)
@PropertySource(value = "classpath:/application.properties",ignoreResourceNotFound = true)
public class SpringConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer  propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
