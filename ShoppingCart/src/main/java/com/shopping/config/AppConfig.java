package com.shopping.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.shopping.dao"),
      @ComponentScan("com.shopping.service")})
@EnableJpaRepositories("com.shopping.repository")
public class AppConfig {
	
	
	@Autowired
	private Environment env;

	@Bean
	 public LocalSessionFactoryBean getSessionFactory() {
	  LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	
	  Properties props = new Properties();
	      // Setting JDBC properties
	  props.put(DRIVER, env.getProperty("mysql.driver"));
	  props.put(URL, env.getProperty("mysql.url"));
	  props.put(USER, env.getProperty("mysql.user"));
	  props.put(PASS, env.getProperty("mysql.password"));
	
	  // Setting Hibernate properties
	  props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
	  props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
	
	  // Setting C3P0 properties
	  props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
	  props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
	  props.put(C3P0_ACQUIRE_INCREMENT, 
	        env.getProperty("hibernate.c3p0.acquire_increment"));
	  props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
	  props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
	
	  factoryBean.setHibernateProperties(props);
	  factoryBean.setPackagesToScan("com.shopping.model");
	
	      return factoryBean;
	   }
	 
	 @Bean
	   public HibernateTransactionManager getTransactionManager() {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(getSessionFactory().getObject());
	      return transactionManager;
	   }
	 @Primary
	 @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource());
	        em.setPackagesToScan(new String[] { "com.shopping.model" });

	        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        vendorAdapter.setDatabase(Database.MYSQL);
	        em.setJpaVendorAdapter(vendorAdapter);
	        em.setJpaProperties(additionalProperties());

	        return em;
	    }
	 
	 @Bean
	    public DataSource dataSource() {
	        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("mysql.driver")));
	        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("mysql.url")));
	        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("mysql.user")));
	        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("mysql.password")));

	        return dataSource;
	    }
	 
	 final Properties additionalProperties() {
	        final Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	         hibernateProperties.setProperty("hibernate.show_sql", "true");
	        return hibernateProperties;
	    }
	 @Bean(name = "transactionManager")
	 @Primary
	 public PlatformTransactionManager transactionManager() {
	     JpaTransactionManager txManager = new JpaTransactionManager();
	     txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	     return txManager;
	 }
	 
	 
}
