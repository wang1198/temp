package com.ctsi.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * ClassName SecondaryDBConfig
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/18 22:36
 * Version v1.0
 **/
@Configuration
@MapperScan(basePackages="com.ctsi.dao",annotationClass= Mapper.class,sqlSessionFactoryRef = "sqlSessionFactory2")
public class SecondaryDBConfig {
    @Autowired
    @Qualifier("secondary")
    private DataSource secondary;

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception{
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
        factoryBean.setDataSource(secondary);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception{
        SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory2());
        return template;
    }
}
