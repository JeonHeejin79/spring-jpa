package com.example.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringJpaApplication.class, args);
	}

	public static void executeDBCP() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/springdata";
		String username = "heejin";
		String password = "pass";

		// try with resource java 8
		// connection 을 만드는 비용이 높다.
		// connection 객체를 pool 로 관리한다. -> dbcp
		// spring boot 같은 경우는  hikari 객체를 사용한다.
		// sql 이 표준도 있기하지만 db 마다 다르다.
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Connection created: " + connection);
			// String sql = "CREATE TABLE ACCOUNT (id int, username varchar(255), password varchar(255));";
			String sql = "INSERT INTO ACCOUNT VALUES(1, 'heejin', 'pass');";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.executeUpdate();
			}
		}
	}

}
