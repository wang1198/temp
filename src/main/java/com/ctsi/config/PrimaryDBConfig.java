package com.ctsi.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * ClassName primaryDBConfig
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/18 22:29
 * Version v1.0
 **/
@Configuration
public class PrimaryDBConfig {
    @Autowired
    @Qualifier("primary")
    private DataSource primary;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
        factoryBean.setDataSource(primary);
        return factoryBean.getObject();
    }
    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception{
        SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory());
        return template;
    }
}
