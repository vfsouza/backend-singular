package dao;

import model.Carrinho;

import java.util.List;

public class CarrinhoDAO extends StatementManager {

    public List<Carrinho> listarTodos() {
        return selectAll(Carrinho.class);
    }

    public Carrinho buscarPorId(int id) {
        return selectById(Carrinho.class, id);
    }

    public Carrinho inserir(Carrinho carrinho) {
        return insert(carrinho);
    }

    public boolean atualizar(Carrinho carrinho) {
        return update(carrinho);
    }

    public boolean deletar(int id) {
        return delete(Carrinho.class, id);
    }
}
