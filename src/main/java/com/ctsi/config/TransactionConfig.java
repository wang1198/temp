package com.ctsi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * ClassName TransactionConfig
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/18 18:11
 * Version v1.0
 **/
@Configuration
public class TransactionConfig implements TransactionManagementConfigurer {
    @Resource(name="tx1")
    private PlatformTransactionManager txManager;
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager;
    }
    @Bean(name="tx1")
    public PlatformTransactionManager dbTransactionManager(@Qualifier("primary")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="tx2")
    public PlatformTransactionManager db2TransactionManager(@Qualifier("secondary")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
