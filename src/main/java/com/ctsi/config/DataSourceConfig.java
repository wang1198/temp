package com.ctsi.config;

import com.ctsi.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName DataSourceConfig
 * Description //TODO
 * Author tongliwei
 * Date 2020/1/31 16:17
 * Version v1.0
 **/
@Configuration
public class DataSourceConfig {
    @Bean(name="primary")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name="secondary")
    @ConfigurationProperties(prefix = "ctsi.datasource.db")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name="dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());
        Map<Object,Object> dsMap=new HashMap<>(5);
        dsMap.put("primary",dataSource1());
        dsMap.put("secondary",dataSource2());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
    /*@Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }*/
}
