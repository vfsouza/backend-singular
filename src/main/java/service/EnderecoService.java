package service;

import com.google.gson.Gson;
import dao.EnderecoDAO;
import interfaces.IService;
import model.Endereco;
import spark.Request;
import spark.Response;

import java.util.List;

public class EnderecoService implements IService {
    private static Gson gson = new Gson();
    private static EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<Endereco> enderecos = enderecoDAO.listarTodos();
        return gson.toJson(enderecos);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Endereco endereco = enderecoDAO.buscarPorId(id);

        if (endereco != null) {
            return gson.toJson(endereco);
        } else {
            response.status(404);
            return "{ \"erro\": \"Endereço não encontrado\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        Endereco novoEndereco = gson.fromJson(request.body(), Endereco.class);
        Endereco enderecoInserido = enderecoDAO.inserir(novoEndereco);

        if (enderecoInserido != null) {
            response.status(201);
            return gson.toJson(enderecoInserido);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir endereço\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");

        int id = Integer.parseInt(request.params(":id"));

        Endereco enderecoAtualizado = gson.fromJson(request.body(), Endereco.class);
        enderecoAtualizado.setId(id);

        boolean sucesso = enderecoDAO.atualizar(enderecoAtualizado);

        if (sucesso) {
            return gson.toJson(enderecoAtualizado);
        } else {
            response.status(404);
            return "{ \"erro\": \"Endereco não encontrado\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = enderecoDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"Endereco deletado com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"Endereco não encontrado\" }";
        }
    }
}
