package com.example.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Bean
	@Profile("!test")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@Profile("test")
	public JdbcTemplate testjdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean(initMethod = "migrate")
	@Profile("!test")
	public Flyway flywayLocal() {
		return Flyway.configure()
				.dataSource(dataSource)
				.baselineOnMigrate(true)
				.locations("classpath:db/migration/h2", "classpath:db/migration/common").load();
	}

	@Bean(initMethod = "migrate")
	@Profile("test")
	public Flyway flyway(DataSource dataSource) {
		return Flyway.configure()
				.dataSource(dataSource)
				.baselineOnMigrate(true)
				.locations("classpath:db/migration/h2").load();
	}

}
