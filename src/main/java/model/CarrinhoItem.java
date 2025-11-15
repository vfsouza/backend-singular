package model;

import annotations.Column;

public class CarrinhoItem {
    private int id;
    @Column(name = "pedidoid")
    private int pedidoId;
    @Column(name = "itemid")
    private int itemId;
    private int quantidade;

    public CarrinhoItem() {
    }

    public CarrinhoItem(int id, int pedidoId, int itemId, int quantidade) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.itemId = itemId;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
