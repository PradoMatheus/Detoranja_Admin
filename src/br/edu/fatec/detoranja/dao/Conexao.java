package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection getConnection() {
		try {
			String server = "localhost";
			String usuario = "postgres";
			String senha = "123456";
			String banco = "Detoranja";
			Class.forName("org.postgresql.Driver");
			String path = "jdbc:postgresql://" + server + "/" + banco;

			Connection conn = DriverManager.getConnection(path, usuario, senha);
			return conn;

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static void fechar(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

		}
	}
}
