package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chenj
 */
@Component
public class DataSourceProperties {

    @Value("${spring.jdbc.datasource.driver}")
    private String  driverClassName = null;

    @Value("${spring.jdbc.datasource.url}")
    private String  url = null;

    @Value("${spring.jdbc.datasource.username}")
    private String  username = null;

    @Value("${spring.jdbc.datasource.password}")
    private String  password = null;



    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
