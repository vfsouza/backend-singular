package service;

import com.google.gson.Gson;
import dao.CarrinhoDAO;
import interfaces.IService;
import model.Carrinho;
import spark.Request;
import spark.Response;

import java.util.List;

public class CarrinhoService implements IService {
    private static Gson gson = new Gson();
    private static CarrinhoDAO carrinhoDAO = new CarrinhoDAO();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<Carrinho> carrinhos = carrinhoDAO.listarTodos();
        return gson.toJson(carrinhos);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Carrinho carrinho = carrinhoDAO.buscarPorId(id);

        if (carrinho != null) {
            return gson.toJson(carrinho);
        } else {
            response.status(404);
            return "{ \"erro\": \"Carrinho não encontrado\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        Carrinho novoCarrinho = gson.fromJson(request.body(), Carrinho.class);
        Carrinho carrinhoInserido = carrinhoDAO.inserir(novoCarrinho);

        if (carrinhoInserido != null) {
            response.status(201);
            return gson.toJson(carrinhoInserido);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir carrinho\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");

        int id = Integer.parseInt(request.params(":id"));

        Carrinho carrinhoAtualizado = gson.fromJson(request.body(), Carrinho.class);
        carrinhoAtualizado.setId(id);

        boolean sucesso = carrinhoDAO.atualizar(carrinhoAtualizado);

        if (sucesso) {
            return gson.toJson(carrinhoAtualizado);
        } else {
            response.status(404);
            return "{ \"erro\": \"Carrinho não encontrado\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = carrinhoDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"Carrinho deletado com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"Carrinho não encontrado\" }";
        }
    }
}
