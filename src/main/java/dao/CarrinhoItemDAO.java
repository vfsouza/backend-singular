package dao;

import model.CarrinhoItem;

import java.util.List;

public class CarrinhoItemDAO extends StatementManager {

    public List<CarrinhoItem> listarTodos() {
        return selectAll(CarrinhoItem.class);
    }

    public CarrinhoItem buscarPorId(int id) {
        return selectById(CarrinhoItem.class, id);
    }

    public CarrinhoItem inserir(CarrinhoItem carrinhoItem) {
        return insert(carrinhoItem);
    }

    public boolean atualizar(CarrinhoItem carrinhoItem) {
        return update(carrinhoItem);
    }

    public boolean deletar(int id) {
        return delete(CarrinhoItem.class, id);
    }
}
