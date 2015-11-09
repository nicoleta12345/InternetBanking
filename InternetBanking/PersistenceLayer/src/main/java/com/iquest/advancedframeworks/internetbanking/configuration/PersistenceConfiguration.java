package com.iquest.advancedframeworks.internetbanking.configuration;


import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.iquest.advancedframeworks.internetbanking")
@ComponentScan({ "com.iquest" })
public class PersistenceConfiguration {

  @Value("${init-db:false}")
  private String initDatabase;

  @Bean
  public PlatformTransactionManager transactionManager() {
    EntityManagerFactory factory = entityManagerFactory().getObject();
    
    return new JpaTransactionManager(factory);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

    vendorAdapter.setGenerateDdl(Boolean.TRUE);
    vendorAdapter.setShowSql(Boolean.TRUE);

    factory.setDataSource(dataSource());
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.iquest.advancedframeworks.internetbanking.model");

    factory.afterPropertiesSet();
    factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    
    return factory;
  }

  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    dataSource.setUrl("jdbc:mysql://localhost:3306/internet_banking");
   
    return dataSource;
  }

}