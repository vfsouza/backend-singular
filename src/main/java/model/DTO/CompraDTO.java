package model.DTO;

import java.sql.Timestamp;
import java.util.List;

public class CompraDTO {
    private int clienteId;
    private double valorTotal;
    private Timestamp dataCriacao;
    private List<CompraItemDTO> compraItemList;

    public CompraDTO() {
    }

    public CompraDTO(int clienteId, double valorTotal, Timestamp dataCriacao, List<CompraItemDTO> compraItemList) {
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.compraItemList = compraItemList;
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

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<CompraItemDTO> getCompraItemList() {
        return compraItemList;
    }

    public void setCarrinhoItemList(List<CompraItemDTO> compraItemList) {
        this.compraItemList = compraItemList;
    }
}
