package service;

import com.google.gson.Gson;
import dao.ProdutoDAO;
import interfaces.IService;
import model.Produto;
import spark.Request;
import spark.Response;

import java.util.List;

public class ProdutoService implements IService {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Gson gson = new Gson();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<Produto> produtos = produtoDAO.listarTodos();
        return gson.toJson(produtos);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Produto produto = produtoDAO.buscarPorId(id);

        if (produto != null) {
            return gson.toJson(produto);
        } else {
            response.status(404);
            return "{ \"erro\": \"Produto não encontrado\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        Produto novoProduto = gson.fromJson(request.body(), Produto.class);
        Produto produtoInserido = produtoDAO.inserir(novoProduto);

        if (produtoInserido != null) {
            response.status(201);
            return gson.toJson(produtoInserido);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir produto\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        Produto produtoAtualizado = gson.fromJson(request.body(), Produto.class);
        produtoAtualizado.setId(id);

        boolean sucesso = produtoDAO.atualizar(produtoAtualizado);

        if (sucesso) {
            return gson.toJson(produtoAtualizado);
        } else {
            response.status(404);
            return "{ \"erro\": \"Produto não encontrado\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = produtoDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"Produto deletado com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"Produto não encontrado\" }";
        }
    }
}
