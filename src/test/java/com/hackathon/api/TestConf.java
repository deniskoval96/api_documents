package com.hackathon.api;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@TestConfiguration
//@Configuration
@MapperScan(value = "com.denis.learn.dao", sqlSessionFactoryRef = "sqlSessionFactory")
//@TestPropertySource("classpath:application-test.properties")
@PropertySource("classpath:application-test.properties")
public class TestConf {

    @Value("${db.driver}")
    private String mysqlDriver;
    @Value("${db.url}")
    private String mysqlUrl;
    @Value("${db.username}")
    private String mysqlUsername;
    @Value("${db.password}")
    private String mysqlPass;

    @Bean
    public DataSource mysqlDataSource() {

        System.out.println("++++++++++++" + mysqlDriver + mysqlUrl);

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(mysqlUrl);
        dataSource.setUser(mysqlUsername);
        dataSource.setPassword(mysqlPass);
        return dataSource;

//        return DataSourceBuilder.create()
//                .driverClassName(mysqlDriver)
//                .url(mysqlUrl)
//                .username(mysqlUsername)
//                .password(mysqlPass)
//                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mysqlDataSource());
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(
                "classpath:conf/mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(mysqlDataSource());
        return manager;
    }

}
