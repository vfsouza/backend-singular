package dao;

import java.util.List;

import model.Cliente;

public class ClienteDAO extends StatementManager {
	public List<Cliente> listarTodos() {
        return selectAll(Cliente.class);
    }
    
    public Cliente buscarPorId(int id) {
        return selectById(Cliente.class, id);
    }
    
    public Cliente inserir(Cliente cliente) {
        return insert(cliente);
    }
    
    public boolean atualizar(Cliente cliente) {
        return update(cliente);
    }
    
    public boolean deletar(int id) {
        return delete(Cliente.class, id);
    }

    public Cliente buscarPorEmail(String email) {
        String nomeTabela = Cliente.class.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + nomeTabela + " WHERE email = ?";

        List<Cliente> resultado = executeQuery(sql, Cliente.class, email);
        return resultado.isEmpty() ? null : resultado.get(0);
    }
}
