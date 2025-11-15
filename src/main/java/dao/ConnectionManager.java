package dao;

import java.sql.*;

public class ConnectionManager {
	
	 public static Connection getConnection() {
	    Connection conexao = null;
	    
	    try {
            String databaseUrl = System.getenv("DATABASE_URL");
            System.out.println(databaseUrl);
            if (databaseUrl != null && !databaseUrl.isEmpty()) {
                // Ambiente de produção (Railway)
                System.out.println("🔗 Conectando ao banco de produção...");
                if (!databaseUrl.startsWith("jdbc:")) {
                    databaseUrl = "jdbc:" + databaseUrl;
                }

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
