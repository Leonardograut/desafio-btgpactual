package com.btgpactual.desafio.listener.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class OrderCreatedEvent {

    @JsonProperty("codigoPedido")
    Long codigoPedido;

    @JsonProperty("codigoCliente")
    Long codigoCliente;

    @JsonProperty("itens")
    List<OrderItemEvent>itens;

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<OrderItemEvent> getItems() {
        return itens;
    }

    public void setItems(List<OrderItemEvent> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "codigoPedido=" + codigoPedido +
                ", codigoCliente=" + codigoCliente +
                ", itens=" + itens +
                '}';
    }
}

