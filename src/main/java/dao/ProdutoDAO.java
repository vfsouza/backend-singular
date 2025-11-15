package dao;

import java.util.List;

import model.Produto;

public class ProdutoDAO extends StatementManager {
	public List<Produto> get() {
        return selectAll(Produto.class);
    }
    
    public Produto getById(int id) {
        return selectById(Produto.class, id);
    }
    
    public Produto create(Produto produto) {
        return insert(produto);
    }
    
    public boolean update(Produto produto) {
        return update(produto);
    }
    
    public boolean delete(int id) {
        return delete(Produto.class, id);
    }
}
