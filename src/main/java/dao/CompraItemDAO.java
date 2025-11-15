package dao;

import java.util.List;

import model.CompraItem;

public class CompraItemDAO extends StatementManager {
	public List<CompraItem> listarTodos() {
        return selectAll(CompraItem.class);
    }
    
    public CompraItem buscarPorId(int id) {
        return selectById(CompraItem.class, id);
    }
    
    public CompraItem inserir(CompraItem compraItem) {
        return insert(compraItem);
    }
    
    public boolean atualizar(CompraItem compraItem) {
        return update(compraItem);
    }
    
    public boolean deletar(int id) {
        return delete(CompraItem.class, id);
    }
}
