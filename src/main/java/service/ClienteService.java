package service;

import com.google.gson.Gson;
import dao.ClienteDAO;
import dao.EnderecoDAO;
import interfaces.IService;
import model.Cliente;
import model.DTO.ClienteDTO;
import model.DTO.EnderecoDTO;
import model.Endereco;
import spark.Request;
import spark.Response;

import java.util.List;

public class ClienteService implements IService {
    private static Gson gson = new Gson();
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<Cliente> clientes = clienteDAO.listarTodos();
        return gson.toJson(clientes);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Cliente cliente = clienteDAO.buscarPorId(id);

        if (cliente != null) {
            return gson.toJson(cliente);
        } else {
            response.status(404);
            return "{ \"erro\": \"Cliente não encontrado\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        ClienteDTO novoCliente = gson.fromJson(request.body(), ClienteDTO.class);
        Cliente inserirCliente = new Cliente(novoCliente.getNome(), novoCliente.getEmail(), novoCliente.getSenha(), false);
        Cliente clienteInserido = clienteDAO.inserir(inserirCliente);

        EnderecoDTO novoEndereco = novoCliente.getEndereco();
        Endereco inserirEndereco = new Endereco();
        inserirEndereco.setIdCliente(clienteInserido.getId());
        inserirEndereco.setCep(novoEndereco.getCep());
        inserirEndereco.setRua(novoEndereco.getRua());
        inserirEndereco.setNumero(novoEndereco.getNumero());
        inserirEndereco.setComplemento(novoEndereco.getComplemento());
        inserirEndereco.setCidade(novoEndereco.getCidade());
        inserirEndereco.setEstado(novoEndereco.getEstado());
        inserirEndereco.setPais(novoEndereco.getPais());

        Endereco enderecoInserido = enderecoDAO.inserir(inserirEndereco);

        if (clienteInserido != null && enderecoInserido != null) {
            response.status(201);
            return gson.toJson(clienteInserido);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir cliente\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");

        int id = Integer.parseInt(request.params(":id"));

        Cliente clienteAtualizado = gson.fromJson(request.body(), Cliente.class);
        clienteAtualizado.setId(id);

        boolean sucesso = clienteDAO.atualizar(clienteAtualizado);

        if (sucesso) {
            return gson.toJson(clienteAtualizado);
        } else {
            response.status(404);
            return "{ \"erro\": \"Cliente não encontrado\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");

        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = clienteDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"Cliente deletado com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"Cliente não encontrado\" }";
        }
    }

    public String getByEmail(Request request, Response response) {
        response.type("application/json");
        String email = request.params(":email");
        Cliente cliente = clienteDAO.buscarPorEmail(email);

        if (cliente != null) {
            return gson.toJson(cliente);
        } else {
            response.status(404);
            return "{ \"erro\": \"Cliente não encontrado\" }";
        }
    }
}
