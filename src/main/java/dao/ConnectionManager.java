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
            String databaseUrl = System.getenv("BACK_DATABASE_URL");
            if (databaseUrl != null && !databaseUrl.isEmpty()) {
                // Ambiente de produção (Railway)
                System.out.println("🔗 Conectando ao banco de produção...");

                // Railway usa formato: postgres://user:pass@host:port/db
                // JDBC precisa: postgresql://user:pass@host:port/db
                databaseUrl = databaseUrl.replace("postgres://", "jdbc:postgresql://");

                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(databaseUrl);

                System.out.println("✅ Conectado ao banco de produção!");

            } else {
                // Ambiente local (desenvolvimento)
                System.out.println("🔗 Conectando ao banco local...");

                String url = "jdbc:postgresql://localhost:5432/singular";
                String usuario = "postgres";
                String senha = "postgres"; // ← Ajuste sua senha local

                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(url, usuario, senha);

                System.out.println("✅ Conectado ao banco local!");
            }
	    } catch (ClassNotFoundException ex) {
	        System.out.println("❌ Driver não encontrado: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch (SQLException ex) {
	        System.out.println("❌ Erro ao conectar: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    
	    return conexao;
	}
}
