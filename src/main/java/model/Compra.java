package model;

import annotations.Column;

public class Compra {
	private int id;
    @Column(name = "idcliente")
	private int clienteId;
	private float valorTotal;
	
	public Compra() {
	}

	public Compra(int id, int clienteId, float valorTotal) {
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

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
