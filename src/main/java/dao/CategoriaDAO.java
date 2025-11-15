package dao;

import model.Categoria;

import java.util.List;

public class CategoriaDAO extends StatementManager {

    public List<Categoria> listarTodos() {
        return selectAll(Categoria.class);
    }

    public Categoria buscarPorId(int id) {
        return selectById(Categoria.class, id);
    }

    public Categoria inserir(Categoria categoria) {
        return insert(categoria);
    }

    public boolean atualizar(Categoria categoria) {
        return update(categoria);
    }

    public boolean deletar(int id) {
        return delete(Categoria.class, id);
    }
}
