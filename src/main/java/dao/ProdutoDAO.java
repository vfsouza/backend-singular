package dao;

import java.util.List;

import model.Produto;

public class ProdutoDAO extends StatementManager {
	public List<Produto> listarTodos() {
        return selectAll(Produto.class);
    }
    
    public Produto buscarPorId(int id) {
        return selectById(Produto.class, id);
    }
    
    public Produto inserir(Produto produto) {
        return insert(produto);
    }
    
    public boolean atualizar(Produto produto) {
        return update(produto);
    }
    
    public boolean deletar(int id) {
        return delete(Produto.class, id);
    }
}
