package dao;

import java.sql.*;
import java.net.URI;

public class ConnectionManager {

    public static Connection getConnection() {
        Connection conexao = null;

        try {
            String databaseUrl = System.getenv("DATABASE_URL");

            if (databaseUrl != null && !databaseUrl.isEmpty()) {
                System.out.println("🔗 Conectando ao banco de produção...");
                System.out.println("📌 URL original: " + databaseUrl.substring(0, 30) + "...");

                // ✅ Parsear a URL do Railway
                URI dbUri = new URI(databaseUrl);

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String host = dbUri.getHost();
                int port = dbUri.getPort();
                String database = dbUri.getPath().substring(1); // Remove a barra inicial

                // Construir URL JDBC correta
                String jdbcUrl = String.format(
                        "jdbc:postgresql://%s:%d/%s",
                        host, port, database
                );

                System.out.println("📌 Host: " + host);
                System.out.println("📌 Port: " + port);
                System.out.println("📌 Database: " + database);
                System.out.println("📌 User: " + username);
                System.out.println("📌 JDBC URL: " + jdbcUrl);

                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(jdbcUrl, username, password);

                System.out.println("========================================");
                System.out.println("✅✅✅ CONECTADO AO BANCO! ✅✅✅");
                System.out.println("========================================");

            } else {
                System.out.println("🔗 Conectando ao banco local...");

                String url = "jdbc:postgresql://localhost:5432/singular";
                String usuario = "postgres";
                String senha = "postgres";

                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(url, usuario, senha);

                System.out.println("✅ Conectado ao banco local!");
            }

        } catch (Exception ex) {
            System.err.println("❌ ERRO: " + ex.getMessage());
            ex.printStackTrace();
        }

        return conexao;
    }
}