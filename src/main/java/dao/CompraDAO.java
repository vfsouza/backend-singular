package dao;

import java.util.List;

import model.Compra;

public class CompraDAO extends StatementManager {
	public List<Compra> listarTodos() {
        return selectAll(Compra.class);
    }
    
    public Compra buscarPorId(int id) {
        return selectById(Compra.class, id);
    }
    
    public Compra inserir(Compra compra) {
        return insert(compra);
    }
    
    public boolean atualizar(Compra compra) {
        return update(compra);
    }
    
    public boolean deletar(int id) {
        return delete(Compra.class, id);
    }
}
