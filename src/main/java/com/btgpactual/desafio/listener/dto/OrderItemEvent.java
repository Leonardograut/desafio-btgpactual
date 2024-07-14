package com.btgpactual.desafio.listener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OrderItemEvent {

    @JsonProperty("produto")
    String produto;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @JsonProperty("quantidade")
    Integer quantidade;

    @JsonProperty("preco")
    BigDecimal preco;

    @Override
    public String toString() {
        return "OrderItemEvent{" +
                "produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +

                '}';

    }
}