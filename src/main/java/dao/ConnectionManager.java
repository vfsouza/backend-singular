package dao;

import java.sql.*;

public class ConnectionManager {
	
	 public static Connection getConnection() {
	    Connection conexao = null;
	    
	    try {
            String databaseUrl = System.getenv("DATABASE_URL");

            System.out.println("========================================");
            System.out.println("🔍 INICIANDO CONEXÃO COM BANCO");
            System.out.println("========================================");
            System.out.println("1️⃣ URL ORIGINAL: " + databaseUrl);

            if (databaseUrl != null && !databaseUrl.isEmpty()) {
                System.out.println("2️⃣ Variável encontrada!");
                System.out.println("3️⃣ Começa com 'jdbc:'? " + databaseUrl.startsWith("jdbc:"));

                // Adicionar jdbc: se não tiver
                if (!databaseUrl.startsWith("jdbc:")) {
                    System.out.println("4️⃣ Adicionando 'jdbc:' no início...");
                    databaseUrl = "jdbc:" + databaseUrl;
                    System.out.println("5️⃣ URL após adicionar jdbc: " + databaseUrl);
                }

                System.out.println("6️⃣ URL FINAL antes do getConnection: " + databaseUrl);
                System.out.println("7️⃣ Carregando driver PostgreSQL...");

                Class.forName("org.postgresql.Driver");

                System.out.println("8️⃣ Driver carregado! Tentando conectar...");

                conexao = DriverManager.getConnection(databaseUrl);

                System.out.println("========================================");
                System.out.println("✅✅✅ SUCESSO! CONECTADO AO BANCO! ✅✅✅");
                System.out.println("========================================");

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
