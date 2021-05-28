package br.edu.fatec.detoranja.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	final private static String server = "sql10.freemysqlhosting.net";
	final private static String usuario = "sql10414616";
	final private static String senha = "S1i69LIPgR";
	final private static String banco = "sql10414616";
	final private static String porta = "3306";

	public static Connection getConnection() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String path = "jdbc:mysql://" + server + ":" + porta + "/" + banco;

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
