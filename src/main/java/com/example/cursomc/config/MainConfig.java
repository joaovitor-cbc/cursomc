package com.example.cursomc.config;

import java.net.URI;
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
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
					+ "?sslmode=require";

			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);

			return basicDataSource;
		}
		return null;
	}
	
}
