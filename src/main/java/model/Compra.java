package model;

import annotations.Column;

import java.sql.Timestamp;

public class Compra {
	private int id;
    @Column(name = "idcliente")
	private int clienteId;
	private double valorTotal;
    @Column(name = "datacriacao")
    private Timestamp dataCriacao = new Timestamp(System.currentTimeMillis());
	
	public Compra() {
	}

    public Compra(int clienteId, double valorTotal, Timestamp dataCriacao) {
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
    }

    public Compra(int id, int clienteId, double valorTotal) {
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

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
