package model;

import annotations.Column;

public class CompraItem {
	private int id;
	@Column(name = "compraid")
	private int compraId;
	@Column(name = "produtoid")
	private int produtoId;
	private int quantidade;
	private float subtotal;
	@Column(name = "precounit")
	private float precoUnit;
	
	public CompraItem() {
	}
	
	public CompraItem(int id, int compraId, int produtoId, int quantidade, float subtotal, float precoUnit) {
		super();
		this.id = id;
		this.compraId = compraId;
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.subtotal = subtotal;
		this.precoUnit = precoUnit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompraId() {
		return compraId;
	}

	public void setCompraId(int compraId) {
		this.compraId = compraId;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(float precoUnit) {
		this.precoUnit = precoUnit;
	}
}
