package com.ldb.truck.DBConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://178.128.214.105:33066/khounkham_logistics_db_EABh1xCQuo");
        dataSource.setUsername("khounkham_logistics_user");
        dataSource.setPassword("eBe12345678$DB");
        return dataSource;
    }
    @Bean(name = "EBankJdbcTemplate")
    public JdbcTemplate EBankJdbcTemplate(DataSource EBankJdbcTemplate) {
        return new JdbcTemplate(EBankJdbcTemplate);
    }
    //----

}
