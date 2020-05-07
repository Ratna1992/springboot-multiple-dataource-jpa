package com.ratna.architecture.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ratna.architecture.utility.ArchitectureUtility;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	UserDetailsService userDetailsService;

	// step1 authentication
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info(ArchitectureUtility.enteredInto("authenticationConfigure"));
		logger.info("Authentication process goes here...");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		logger.info(ArchitectureUtility.exitedFrom("authenticationConfigure"));
	}

	// step2 authorization
	@Override
	public void configure(HttpSecurity http) throws Exception {
		logger.info(ArchitectureUtility.enteredInto("authorizationConfigure"));
		logger.info("Authorization process goes here...");
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasRole("USER")
				.antMatchers("/").permitAll().and().formLogin();
		logger.info(ArchitectureUtility.exitedFrom("authorizationConfigure"));

	}

	// step3 password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info(ArchitectureUtility.enteredInto("passwordEncoder"));
		logger.info("PasswordEncoder process goes here...");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		logger.info(ArchitectureUtility.exitedFrom("passwordEncoder"));
		return encoder;
	}
}
