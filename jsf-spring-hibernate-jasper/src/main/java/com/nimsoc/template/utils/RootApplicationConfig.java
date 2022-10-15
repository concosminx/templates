package com.nimsoc.template.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.nimsoc.template"})
public class RootApplicationConfig {

  @Bean
  PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
    PropertyPlaceholderConfigurer b = new PropertyPlaceholderConfigurer();
    b.setIgnoreUnresolvablePlaceholders(true);
    b.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
    b.setSearchSystemEnvironment(true);
    b.setIgnoreResourceNotFound(true);
    b.setLocations(
        new ClassPathResource("META-INF/applicationDefaults.properties"),
        new ClassPathResource("META-INF/applicationContext.properties"),
        new ClassPathResource("application.properties")
    );

    return b;
  }


  @Bean(name = "dataSourceCore")
  public DataSource dataSourceCore() {
    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
    return lookup.getDataSource("jdbc/MAINDS");
  }

  @Bean(name = "dataSource")
  public TransactionAwareDataSourceProxy dataSource() {
    TransactionAwareDataSourceProxy transactionAwareDataSourceProxy = new TransactionAwareDataSourceProxy(dataSourceCore());
    return transactionAwareDataSourceProxy;
  }

  @Bean(name = "sessionFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
    localSessionFactoryBean.setDataSource(dataSourceCore());
    localSessionFactoryBean.setConfigLocation(new ClassPathResource("/META-INF/hibernate.cfg.xml"));
    localSessionFactoryBean.setHibernateProperties(hibernateProperties());
    localSessionFactoryBean.setPackagesToScan("com.nimsoc.template.**");
    return localSessionFactoryBean;
  }

  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    transactionManager.setDataSource(dataSourceCore());
    return transactionManager;
  }

  private final Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.show_sql", "true");
    hibernateProperties.setProperty("hibernate.jdbc.batch_size", "50");
    hibernateProperties.setProperty("hibernate.jdbc.fetch_size", "50");

    return hibernateProperties;
  }
}
