package dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class StatementManager {
    public static <T> List<T> selectAll(Class<T> classType) {
        String nomeTabela = classType.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + nomeTabela;
        
        return executeQuery(sql, classType);
    }

    public static <T> T selectById(Class<T> classType, int id) {
        String nomeTabela = classType.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + nomeTabela + " WHERE id = ?";
        
        List<T> resultado = executeQuery(sql, classType, id);
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public static <T> List<T> selectWhere(Class<T> classType, Object param) {
        String nomeTabela = classType.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + nomeTabela;
        
        return executeQuery(sql, classType, param);
    }

    public static <T> T insert(T objeto) {
        try (Connection conexao = ConnectionManager.getConnection()) {
            Class<?> classe = objeto.getClass();
            String nomeTabela = classe.getSimpleName().toLowerCase();
            
            Field[] campos = classe.getDeclaredFields();
            List<String> nomeCampos = new ArrayList<>();
            List<Object> valores = new ArrayList<>();
            
            // Monta lista de campos e valores (exceto ID)
            for (Field campo : campos) {
                campo.setAccessible(true);
                
                if (campo.getName().equalsIgnoreCase("id")) {
                    continue;
                }
                
                nomeCampos.add(campo.getName().toLowerCase());
                valores.add(campo.get(objeto));
            }
            
            // Gera SQL: INSERT INTO tabela (campo1, campo2) VALUES (?, ?)
            String colunas = String.join(", ", nomeCampos);
            String placeholders = String.join(", ", java.util.Collections.nCopies(nomeCampos.size(), "?"));
            String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", nomeTabela, colunas, placeholders);
            
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // Define os parâmetros
            for (int i = 0; i < valores.size(); i++) {
                stmt.setObject(i + 1, valores.get(i));
            }
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Pega o ID gerado e atribui ao objeto
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    
                    for (Field campo : campos) {
                        campo.setAccessible(true);
                        if (campo.getName().equalsIgnoreCase("id")) {
                            campo.set(objeto, idGerado);
                            break;
                        }
                    }
                }
                rs.close();
                stmt.close();
                
                return objeto; // Retorna objeto com ID preenchido
            }
            
            stmt.close();
            
        } catch (Exception ex) {
            System.out.println("Erro ao inserir: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return null;
    }

    public static <T> boolean update(T objeto) {
        try (Connection conexao = ConnectionManager.getConnection()) {
            Class<?> classe = objeto.getClass();
            String nomeTabela = classe.getSimpleName().toLowerCase();
            
            Field[] campos = classe.getDeclaredFields();
            List<String> sets = new ArrayList<>();
            List<Object> valores = new ArrayList<>();
            Object idValor = null;
            
            // Monta lista de SET campo = ? (exceto ID)
            for (Field campo : campos) {
                campo.setAccessible(true);
                
                if (campo.getName().equalsIgnoreCase("id")) {
                    idValor = campo.get(objeto);
                    continue;
                }
                
                sets.add(campo.getName().toLowerCase() + " = ?");
                valores.add(campo.get(objeto));
            }
            
            if (idValor == null) {
                System.out.println("Erro: ID não encontrado no objeto");
                return false;
            }
            
            // Adiciona ID no final para o WHERE
            valores.add(idValor);
            
            // Gera SQL: UPDATE tabela SET campo1 = ?, campo2 = ? WHERE id = ?
            String sql = String.format("UPDATE %s SET %s WHERE id = ?", 
                                       nomeTabela, String.join(", ", sets));
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            // Define os parâmetros
            for (int i = 0; i < valores.size(); i++) {
                stmt.setObject(i + 1, valores.get(i));
            }
            
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            
            return rowsAffected > 0;
            
        } catch (Exception ex) {
            System.out.println("Erro ao atualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return false;
    }

    public static <T> boolean delete(Class<T> classType, int id) {
        String nomeTabela = classType.getSimpleName().toLowerCase();
        String sql = "DELETE FROM " + nomeTabela + " WHERE id = ?";
        
        try (Connection conexao = ConnectionManager.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return false;
    }

    public static <T> boolean delete(T objeto) {
        try {
            Class<?> classe = objeto.getClass();
            Field[] campos = classe.getDeclaredFields();
            
            // Procura o campo ID
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getName().equalsIgnoreCase("id")) {
                    int id = (int) campo.get(objeto);
                    return delete(classe, id);
                }
            }
            
            System.out.println("Erro: ID não encontrado no objeto");
            
        } catch (Exception ex) {
            System.out.println("Erro ao deletar: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return false;
    }

    public static <T> List<T> executeQuery(String sql, Class<T> classType, Object... params) {
        List<T> resultados = new ArrayList<>();
        
        try (Connection conexao = ConnectionManager.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            // Define os parâmetros
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                T objeto = classType.getDeclaredConstructor().newInstance();
                Field[] campos = classType.getDeclaredFields();
                
                for (Field campo : campos) {
                    campo.setAccessible(true);
                    String nomeCampo = campo.getName().toLowerCase();
                    
                    try {
                        Object valor = rs.getObject(nomeCampo);
                        if (valor != null) {
                            campo.set(objeto, valor);
                        }
                    } catch (SQLException e) {
                        // Coluna não existe no ResultSet
                    }
                }
                
                resultados.add(objeto);
            }
            
            rs.close();
            
        } catch (Exception ex) {
            System.out.println("Erro na query: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return resultados;
	}
}
