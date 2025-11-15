package dao;

import java.sql.*;

public class ConnectionManager {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String SERVER = "localhost";
	private static final String DATABASE = "singular";
	private static final int PORT = 5432;
	private static final String URL = "jdbc:postgresql://" + SERVER + ":" + PORT + "/" + DATABASE;
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "679165";
	
	 public static Connection getConnection() {
	    Connection conexao = null;
	    
	    try {
	        Class.forName(DRIVER);
	        conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        System.out.println("✅ Conexão estabelecida com sucesso!");
	    } catch (ClassNotFoundException ex) {
	        System.out.println("❌ Driver não encontrado: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch (SQLException ex) {
	        System.out.println("❌ Erro ao conectar: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    
	    return conexao;
	}
	
	public static void closeConnection(Connection conn) {
	    if (conn != null) {
	        try {
	            conn.close();
	            System.out.println("✅ Conexão fechada");
	        } catch (SQLException ex) {
	            System.out.println("❌ Erro ao fechar conexão: " + ex.getMessage());
	        }
	    }
	}
}
