package model;

import annotations.Column;

import java.math.BigDecimal;

public class CompraItem {
	private int id;
	@Column(name = "compraid")
	private int compraId;
	@Column(name = "produtoid")
	private int produtoId;
	private int quantidade;
	private BigDecimal subtotal;
	@Column(name = "precounit")
	private BigDecimal precoUnit;
	
	public CompraItem() {
	}
	
	public CompraItem(int id, int compraId, int produtoId, int quantidade, BigDecimal subtotal, BigDecimal precoUnit) {
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

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(BigDecimal precoUnit) {
		this.precoUnit = precoUnit;
	}
}
