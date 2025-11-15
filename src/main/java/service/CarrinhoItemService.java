package service;

import com.google.gson.Gson;
import dao.CarrinhoItemDAO;
import interfaces.IService;
import model.CarrinhoItem;
import spark.Request;
import spark.Response;

import java.util.List;

public class CarrinhoItemService implements IService {
    private static Gson gson = new Gson();
    private static CarrinhoItemDAO carrinhoItemDAO = new CarrinhoItemDAO();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<CarrinhoItem> carrinhoItems = carrinhoItemDAO.listarTodos();
        return gson.toJson(carrinhoItems);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        CarrinhoItem carrinhoItem = carrinhoItemDAO.buscarPorId(id);

        if (carrinhoItem != null) {
            return gson.toJson(carrinhoItem);
        } else {
            response.status(404);
            return "{ \"erro\": \"CarrinhoItem não encontrado\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        CarrinhoItem novoCarrinhoItem = gson.fromJson(request.body(), CarrinhoItem.class);
        CarrinhoItem carrinhoItemInserido = carrinhoItemDAO.inserir(novoCarrinhoItem);

        if (carrinhoItemInserido != null) {
            response.status(201);
            return gson.toJson(carrinhoItemInserido);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir CarrinhoItem\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");

        int id = Integer.parseInt(request.params(":id"));

        CarrinhoItem carrinhoItemAtualizado = gson.fromJson(request.body(), CarrinhoItem.class);
        carrinhoItemAtualizado.setId(id);

        boolean sucesso = carrinhoItemDAO.atualizar(carrinhoItemAtualizado);

        if (sucesso) {
            return gson.toJson(carrinhoItemAtualizado);
        } else {
            response.status(404);
            return "{ \"erro\": \"CarrinhoItem não encontrado\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = carrinhoItemDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"CarrinhoItem deletado com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"CarrinhoItem não encontrado\" }";
        }
    }
}
