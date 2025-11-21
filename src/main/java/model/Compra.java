package model;

import annotations.Column;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Compra {
	private int id;
    @Column(name = "idcliente")
	private int clienteId;
	private BigDecimal valorTotal;
    @Column(name = "datacriacao")
    private Timestamp dataCriacao = new Timestamp(System.currentTimeMillis());
	
	public Compra() {
	}

    public Compra(int clienteId, BigDecimal valorTotal, Timestamp dataCriacao) {
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
    }

    public Compra(int id, int clienteId, BigDecimal valorTotal) {
		this.id = id;
		this.clienteId = clienteId;
		this.valorTotal = valorTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
