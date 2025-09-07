package com.pagamentoservico.pagamento.model;

public class PagamentoRequisicao {
    private Long pedidoId;
    private Double valor;

    // Getters and Setters
    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}