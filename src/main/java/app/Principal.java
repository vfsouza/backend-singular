package app;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;

import model.*;
import service.*;

public class Principal {
    private static ClienteService clienteService = new ClienteService();
    private static EnderecoService enderecoService = new EnderecoService();
    private static CategoriaService categoriaService = new CategoriaService();
    private static ProdutoService produtoService = new ProdutoService();
	private static CompraService compraService = new CompraService();
	private static CompraItemService compraItemService = new CompraItemService();
    private static CarrinhoService carrinhoService = new CarrinhoService();
    private static CarrinhoItemService carrinhoItemService = new CarrinhoItemService();

    public static void main(String[] args) {
        port(8080);

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.header("Access-Control-Allow-Credentials", "true");
            response.type("application/json");
        });

        // Cliente

        // GET - Listar todos os clientes
        get("/cliente", (request, response) -> clienteService.getAll(request, response));
        // GET - Buscar cliente por ID
        get("/cliente/:id", (request, response) -> clienteService.getById(request, response));
        // GET - Buscar cliente por Email
        get("/cliente/email/:email", (request, response) -> clienteService.getByEmail(request, response));
        // POST - Criar novo cliente
        post("/cliente", (request, response) -> clienteService.create(request, response));
        // PUT - Atualizar cliente
        put("/cliente/:id", (request, response) -> clienteService.update(request, response));
        // DELETE - Deletar cliente
        delete("/cliente/:id", (request, response) -> clienteService.delete(request, response));

        // Endereço

        // GET - Listar todos os endereços
        get("/cliente/endereco", (request, response) -> enderecoService.getAll(request, response));
        // GET - Buscar endereço por ID
        get("/cliente/endereco/:id", (request, response) -> enderecoService.getById(request, response));
        // POST - Criar novo endereço
        post("/cliente/endereco", (request, response) -> enderecoService.create(request, response));
        // PUT - Atualizar endereço
        put("/cliente/endereco/:id", (request, response) -> enderecoService.update(request, response));
        // DELETE - Deletar endereço
        delete("/cliente/endereco/:id", (request, response) -> enderecoService.delete(request, response));

        // Categoria

        // GET - Listar todas as categorias
        get("/categoria", (request, response) -> categoriaService.getAll(request, response));
        // GET - Buscar categoria por ID
        get("/categoria/:id", (request, response) -> categoriaService.getById(request, response));
        // POST - Criar novo categoria
        post("/categoria", (request, response) -> categoriaService.create(request, response));
        // PUT - Atualizar categoria
        put("/categoria/:id", (request, response) -> categoriaService.update(request, response));
        // DELETE - Deletar categoria
        delete("/categoria/:id", (request, response) -> categoriaService.delete(request, response));

        // Produto

        // GET - Listar todos os produtos
        get("/produto", (request, response) -> produtoService.getAll(request, response));
        // GET - Buscar produto por ID
        get("/produto/:id", (request, response) -> produtoService.getById(request, response));
        // POST - Criar novo produto
        post("/produto", (request, response) -> produtoService.create(request, response));
        // PUT - Atualizar produto
        put("/produto/:id", (request, response) -> produtoService.update(request, response));
        // DELETE - Deletar produto
        delete("/produto/:id", (request, response) -> produtoService.delete(request, response));

        // Compra

        // GET - Listar todas as compras
        get("/compra", (request, response) -> compraService.getAll(request, response));
        // GET - Buscar compra por ID
        get("/compra/:id", (request, response) -> compraService.getById(request, response));
        // POST - Criar nova compra
        post("/compra", (request, response) -> compraService.create(request, response));
        // PUT - Atualizar compra
        put("/compra/:id", (request, response) -> compraService.update(request, response));
        // DELETE - Deletar compra
        delete("/compra/:id", (request, response) -> compraService.delete(request, response));

        // CompraItem

        // GET - Listar todos os compraItems
        get("/compra-item", (request, response) -> compraItemService.getAll(request, response));
        // GET - Buscar compraItem por ID
        get("/compra-item/:id", (request, response) -> compraItemService.getById(request, response));
        // POST - Criar novo compraItem
        post("/compra-item", (request, response) -> compraItemService.create(request, response));
        // PUT - Atualizar compraItem
        put("/compra-item/:id", (request, response) -> compraItemService.update(request, response));
        // DELETE - Deletar compraItem
        delete("/compra-item/:id", (request, response) -> compraItemService.delete(request, response));

        // Carrinho

        // GET - Listar todos os carrinhos
        get("/carrinho", (request, response) -> carrinhoService.getAll(request, response));
        // GET - Buscar carrinho por ID
        get("/carrinho/:id", (request, response) -> carrinhoService.getById(request, response));
        // POST - Criar novo carrinho
        post("/carrinho", (request, response) -> carrinhoService.create(request, response));
        // PUT - Atualizar carrinho
        put("/carrinho/:id", (request, response) -> carrinhoService.update(request, response));
        // DELETE - Deletar carrinho
        delete("/carrinho/:id", (request, response) -> carrinhoService.delete(request, response));

        // CarrinhoItem

        // GET - Listar todos os carrinhoItems
        get("/carrinho-item", (request, response) -> carrinhoItemService.getAll(request, response));
        // GET - Buscar carrinhoItem por ID
        get("/carrinho-item/:id", (request, response) -> carrinhoItemService.getById(request, response));
        // POST - Criar novo carrinhoItem
        post("/carrinho-item", (request, response) -> carrinhoItemService.create(request, response));
        // PUT - Atualizar carrinhoItem
        put("/carrinho-item/:id", (request, response) -> carrinhoItemService.update(request, response));
        // DELETE - Deletar carrinhoItem
        delete("/carrinho-item/:id", (request, response) -> carrinhoItemService.delete(request, response));

        System.out.println("\n✅ Servidor rodando em: http://localhost:8080\n");
    }
}
