package model;

import annotations.Column;

public class Carrinho {
    private int id;
    @Column(name = "clienteid")
    private int clienteId;

    public Carrinho() {
    }

    public Carrinho(int id, int clienteId) {
        this.id = id;
        this.clienteId = clienteId;
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
}
