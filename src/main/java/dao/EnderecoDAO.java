package dao;

import model.Endereco;

import java.util.List;

public class EnderecoDAO extends StatementManager {

    public List<Endereco> listarTodos() {
        return selectAll(Endereco.class);
    }

    public Endereco buscarPorId(int id) {
        return selectById(Endereco.class, id);
    }

    public Endereco inserir(Endereco enderecoItem) {
        return insert(enderecoItem);
    }

    public boolean atualizar(Endereco enderecoItem) {
        return update(enderecoItem);
    }

    public boolean deletar(int id) {
        return delete(Endereco.class, id);
    }
}
