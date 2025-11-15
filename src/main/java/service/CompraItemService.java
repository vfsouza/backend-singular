package service;

import com.google.gson.Gson;
import dao.CompraItemDAO;
import interfaces.IService;
import model.CompraItem;
import spark.Request;
import spark.Response;

import java.util.List;

public class CompraItemService implements IService {
    private static CompraItemDAO compraItemDAO = new CompraItemDAO();
    private static Gson gson = new Gson();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<CompraItem> compraItems = compraItemDAO.listarTodos();
        return gson.toJson(compraItems);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        CompraItem compraItem = compraItemDAO.buscarPorId(id);

        if (compraItem != null) {
            return gson.toJson(compraItem);
        } else {
            response.status(404);
            return "{ \"erro\": \"CompraItem não encontrado\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        CompraItem novaCompraItem = gson.fromJson(request.body(), CompraItem.class);
        CompraItem compraItemInserida = compraItemDAO.inserir(novaCompraItem);

        if (compraItemInserida != null) {
            response.status(201);
            return gson.toJson(compraItemInserida);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir compraItem\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        CompraItem compraItemAtualizado = gson.fromJson(request.body(), CompraItem.class);
        compraItemAtualizado.setId(id);

        boolean sucesso = compraItemDAO.atualizar(compraItemAtualizado);

        if (sucesso) {
            return gson.toJson(compraItemAtualizado);
        } else {
            response.status(404);
            return "{ \"erro\": \"CompraItem não encontrado\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = compraItemDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"CompraItem deletado com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"CompraItem não encontrado\" }";
        }
    }
}
