package service;

import com.google.gson.Gson;
import dao.CategoriaDAO;
import interfaces.IService;
import model.Categoria;
import spark.Request;
import spark.Response;

import java.util.List;

public class CategoriaService implements IService {
    private static Gson gson = new Gson();
    private static CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<Categoria> categorias = categoriaDAO.listarTodos();
        return gson.toJson(categorias);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Categoria categoria = categoriaDAO.buscarPorId(id);

        if (categoria != null) {
            return gson.toJson(categoria);
        } else {
            response.status(404);
            return "{ \"erro\": \"Categoria não encontrada\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        Categoria novaCategoria = gson.fromJson(request.body(), Categoria.class);
        Categoria categoriaInserida = categoriaDAO.inserir(novaCategoria);

        if (categoriaInserida != null) {
            response.status(201);
            return gson.toJson(categoriaInserida);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir categoria\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");

        int id = Integer.parseInt(request.params(":id"));

        Categoria categoriaAtualizada = gson.fromJson(request.body(), Categoria.class);
        categoriaAtualizada.setId(id);

        boolean sucesso = categoriaDAO.atualizar(categoriaAtualizada);

        if (sucesso) {
            return gson.toJson(categoriaAtualizada);
        } else {
            response.status(404);
            return "{ \"erro\": \"Categoria não encontrada\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = categoriaDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"Categoria deletada com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"Categoria não encontrada\" }";
        }
    }
}
