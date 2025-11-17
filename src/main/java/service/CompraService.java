package service;

import com.google.gson.Gson;
import dao.CompraDAO;
import dao.CompraItemDAO;
import dao.ProdutoDAO;
import interfaces.IService;
import model.Compra;
import model.CompraItem;
import model.DTO.CompraDTO;
import model.Produto;
import spark.Request;
import spark.Response;

import java.util.List;

public class CompraService implements IService {
    private static CompraDAO compraDAO = new CompraDAO();
    private static CompraItemDAO compraItemDAO = new CompraItemDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Gson gson = new Gson();

    @Override
    public String getAll(Request request, Response response) {
        response.type("application/json");
        List<Compra> compras = compraDAO.listarTodos();
        return gson.toJson(compras);
    }

    @Override
    public String getById(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Compra compra = compraDAO.buscarPorId(id);

        if (compra != null) {
            return gson.toJson(compra);
        } else {
            response.status(404);
            return "{ \"erro\": \"Compra não encontrada\" }";
        }
    }

    @Override
    public String create(Request request, Response response) {
        response.type("application/json");

        CompraDTO novaCompra = gson.fromJson(request.body(), CompraDTO.class);
        Compra inserirCompra = new Compra(novaCompra.getClienteId(), novaCompra.getValorTotal(), novaCompra.getDataCriacao());
        Compra compraInserida = compraDAO.inserir(inserirCompra);
        List<Integer> produtosId = novaCompra.getCompraItemList().stream().map((item) -> item.getProdutoId()).toList();

        novaCompra.getCompraItemList().forEach(compraItemDTO -> {
            CompraItem carrinhoItem = new CompraItem();
            carrinhoItem.setCompraId(compraInserida.getId());
            carrinhoItem.setProdutoId(compraItemDTO.getProdutoId());
            carrinhoItem.setQuantidade(compraItemDTO.getQuantidade());
            carrinhoItem.setPrecoUnit(compraItemDTO.getPrecoUnitario());
            carrinhoItem.setSubtotal(compraItemDTO.getQuantidade() * compraItemDTO.getPrecoUnitario());
            compraItemDAO.inserir(carrinhoItem);

            Produto produto = produtoDAO.buscarPorId(compraItemDTO.getProdutoId());
            produto.setEstoque(produto.getEstoque() - compraItemDTO.getQuantidade());
            produtoDAO.atualizar(produto);
        });

        if (compraInserida != null) {
            response.status(201);
            return gson.toJson(compraInserida);
        } else {
            response.status(500);
            return "{ \"erro\": \"Falha ao inserir compra\" }";
        }
    }

    @Override
    public String update(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        Compra compraAtualizada = gson.fromJson(request.body(), Compra.class);
        compraAtualizada.setId(id);

        boolean sucesso = compraDAO.atualizar(compraAtualizada);

        if (sucesso) {
            return gson.toJson(compraAtualizada);
        } else {
            response.status(404);
            return "{ \"erro\": \"Compra não encontrada\" }";
        }
    }

    @Override
    public String delete(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));

        boolean sucesso = compraDAO.deletar(id);

        if (sucesso) {
            return "{ \"mensagem\": \"Compra deletada com sucesso\" }";
        } else {
            response.status(404);
            return "{ \"erro\": \"Compra não encontrada\" }";
        }
    }
}
