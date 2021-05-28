package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection getConnection() {
		try {
			String server = "sql10.freemysqlhosting.net";
			String usuario = "sql10414616";
			String senha = "S1i69LIPgR";
			String banco = "sql10414616";
			Class.forName("com.mysql.jdbc.Driver");
			String path = "jdbc:mysql//" + server + "/" + banco;

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
