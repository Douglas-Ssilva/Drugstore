package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {

	private static String url = "jdbc:mysql://localhost:3306/drogaria_full?serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";
	private Connection connection = null;

	{
		conectar();
		System.out.println("Iniciando blobo de inicialização");
	}

	private void conectar() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // N�o colocar acarreta erro depois ao juntar mais classes
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Success");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error ao conectar"); // Jogando a exception p runtime do java
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void closedConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
