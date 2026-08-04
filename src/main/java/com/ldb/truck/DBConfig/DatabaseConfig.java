package com.ldb.truck.DBConfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.ldb.truck.Repository")
public class DatabaseConfig {

    @Value("${spring.datasource.url:jdbc:mariadb://178.128.214.105:33066/khounkham_logistics_db_EABh1xCQuo}")
    private String dbUrl;

    @Value("${spring.datasource.username:khounkham_logistics_user}")
    private String dbUsername;

    @Value("${DB_PASSWORD:${spring.datasource.password:eBe12345678$DB}}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name:org.mariadb.jdbc.Driver}")
    private String dbDriverClassName;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        // HikariCP Connection Pool Settings for High Performance & Production Safety
        dataSource.setMaximumPoolSize(20);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(300000); // 5 minutes
        dataSource.setConnectionTimeout(20000); // 20 seconds
        dataSource.setMaxLifetime(1200000); // 20 minutes

        return dataSource;
    }

    @Bean(name = "EBankJdbcTemplate")
    public JdbcTemplate EBankJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
