package com.ratna.architecture.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ratna.architecture.utility.ArchitectureUtility;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "testEntityManagerFactory", basePackages = {
		"com.ratna.architecture.testrepository" }, transactionManagerRef = "testTransactionManager") // repository
public class HibernateMySQLConfigurationTestSchema {
	@Autowired
	Environment prop;

	private Logger logger = LoggerFactory.getLogger(HibernateMySQLConfigurationTestSchema.class);

	// step1 create datasource
	@Bean(name = "mySqlDataSourceTestSchema")
	@ConfigurationProperties(prefix = "spring.second.datasource")
	public DataSource mySqlDataSourceTestSchema() {
		logger.info(ArchitectureUtility.enteredInto("mySqlDataSourceTestSchema"));
		try {
			if (prop.getProperty("isJndiRequired").equalsIgnoreCase("false")) {
				logger.info("Creating datasource without Jndi");
				return DataSourceBuilder.create()
						.driverClassName(prop.getProperty("spring.second.datasource.driver-class-name"))
						.password(prop.getProperty("spring.second.datasource.password"))
						.url(prop.getProperty("spring.second.datasource.url"))
						.username(prop.getProperty("spring.second.datasource.data-username")).build();
			} else {
				logger.info("Creating datasource with JNDI");
				final JndiDataSourceLookup lookup = new JndiDataSourceLookup();
				lookup.setResourceRef(true);
				DataSource dataSource = lookup.getDataSource("jdbc/test");
				return dataSource;

			}
		} catch (Exception e) {
			logger.info(ArchitectureUtility.exitedFrom("mySqlDataSourceTestSchema"));
			logger.error(e.getMessage());
			return null;
		}
	}

	// step2 create entityManagerFactory using
	// LocalContainerEntityManagerFactoryBean

	@Bean(name = "testEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mySqlDataSourceTestSchema") DataSource ds) {
		logger.info(ArchitectureUtility.enteredInto("entityManagerFactory"));
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		logger.info("created entityManager by using datasource and adding necessary properties");
		logger.info(ArchitectureUtility.exitedFrom("entityManagerFactory"));
		return builder.dataSource(ds).properties(properties).packages("com.ratna.architecture.testmodel")
				.persistenceUnit("testSchema").build();

	}

	// step 3 create transaction manager
	// using PlatformTransactionManager

	@Bean(name = "testTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("testEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		logger.info(ArchitectureUtility.enteredInto("transactionManager"));
		logger.info("created transactionManager by using entityManagerFactory");
		logger.info(ArchitectureUtility.exitedFrom("transactionManager"));
		return new JpaTransactionManager(entityManagerFactory);

	}

}
