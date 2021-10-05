package com.example.ecommerceapi.config;

import java.net.URISyntaxException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

	@Value("${spring.profiles.active}")
	private String db;

	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {

		if (db.equals("test")) {
			String username = "sa";
			String password = "";
			String dbUrl = "jdbc:h2:mem:testdb";

			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);

			return basicDataSource;
		}
		
		if(db.equals("dev")) {
			String username = "root";
			String password = "";
			String dbUrl = "jdbc:mysql://localhost:3306/curso_spring";

			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);

			return basicDataSource;
		}
		
		if(db.equals("prod")) {

			String username = "postgres";
			String password = "admin";
			String dbUrl = "jdbc:postgresql://localhost:5432/curso_spring";

			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);

			return basicDataSource;
		}
		return null;
	}
	
}
