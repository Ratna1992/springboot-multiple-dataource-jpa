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
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "architectureEntityManagerFactory", basePackages = {
		"com.ratna.architecture.architecturereposoitory" }, transactionManagerRef = "architectureTransactionManager") // repository
public class HibernateMySQLConfigurationArchitectureSchema {
	@Autowired
	Environment prop;

	private Logger logger = LoggerFactory.getLogger(HibernateMySQLConfigurationArchitectureSchema.class);

	// step1 create datasource

	@Primary
	@Bean(name = "mySqlDataSourceArchitectureSchema")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource mySqlDataSourceArchitectureSchema() {
		logger.info(ArchitectureUtility.enteredInto("mySqlDataSourceArchitectureSchema"));
		try {
			if (prop.getProperty("isJndiRequired").equalsIgnoreCase("false")) {
				logger.info("Creating datasource without Jndi");
				return DataSourceBuilder.create()
						.driverClassName(prop.getProperty("spring.datasource.driver-class-name"))
						.password(prop.getProperty("spring.datasource.password"))
						.url(prop.getProperty("spring.datasource.url"))
						.username(prop.getProperty("spring.datasource.data-username")).build();
			} else {
				logger.info("Creating datasource with JNDI");
				final JndiDataSourceLookup lookup = new JndiDataSourceLookup();
				lookup.setResourceRef(true);
				DataSource dataSource = lookup.getDataSource("jdbc/architecture");
				return dataSource;
			}

		} catch (Exception e) {
			logger.info(ArchitectureUtility.exitedFrom("mySqlDataSourceArchitectureSchema"));
			logger.error(e.getMessage());
			return null;
		}

	}

	// step2 create entityManagerFactory using
	// LocalContainerEntityManagerFactoryBean

	@Primary
	@Bean(name = "architectureEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mySqlDataSourceArchitectureSchema") DataSource ds) {
		logger.info(ArchitectureUtility.enteredInto("entityManagerFactory"));
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		logger.info("created entityManager by using datasource and adding necessary properties");
		logger.info(ArchitectureUtility.exitedFrom("entityManagerFactory"));
		return builder.dataSource(ds).properties(properties).packages("com.ratna.architecture.architecturemodel")
				.persistenceUnit("architectureSchema").build();

	}

	// step 3 create transaction manager
	// using PlatformTransactionManager

	@Primary
	@Bean(name = "architectureTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("architectureEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		logger.info(ArchitectureUtility.enteredInto("transactionManager"));
		logger.info("created transactionManager by using entityManagerFactory");
		logger.info(ArchitectureUtility.exitedFrom("transactionManager"));
		return new JpaTransactionManager(entityManagerFactory);

	}

}
