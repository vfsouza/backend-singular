package model;

import java.math.BigDecimal;

public class Produto {
	private int id;
    private int categoriaId;
	private BigDecimal preco;
	private String nome;
	private String cor;
	private String tipo;
    private String imagemUri;
    private int estoque;

	public Produto() {
	}

	public Produto(int id, int categoriaId, BigDecimal valor, String nome, String cor, String tipo, String imagemUri, int estoque) {
		this.id = id;
        this.categoriaId = categoriaId;
		this.preco = valor;
		this.nome = nome;
		this.cor = cor;
		this.tipo = tipo;
        this.imagemUri = imagemUri;
        this.estoque = estoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    public String getImagemUri() {
        return imagemUri;
    }

    public void setImagemUri(String imagemUri) {
        this.imagemUri = imagemUri;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
