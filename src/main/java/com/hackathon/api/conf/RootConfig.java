package com.hackathon.api.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.hackathon.api.dao", sqlSessionFactoryRef = "sqlSessionFactory")
@PropertySource("classpath:application.properties")
public class RootConfig {

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

//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setURL(mysqlUrl);
//        dataSource.setUser(mysqlUsername);
//        dataSource.setPassword(mysqlPass);
//        return dataSource;

        return DataSourceBuilder.create()
                .driverClassName(mysqlDriver)
                .url(mysqlUrl)
                .username(mysqlUsername)
                .password(mysqlPass)
                .build();
    }


    // ToDo:
    //  change with H2
    @Bean
    public DataSource h2DataSource() {

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb")
                .username("sa")
                .password("password")
                .build();

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("h2DataSource") final DataSource dataSource) {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("db/schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("db/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(mysqlDataSource());
        factoryBean.setDataSource(h2DataSource());
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(
                "classpath:conf/mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
//        manager.setDataSource(mysqlDataSource());
        manager.setDataSource(h2DataSource());
        return manager;
    }

}
