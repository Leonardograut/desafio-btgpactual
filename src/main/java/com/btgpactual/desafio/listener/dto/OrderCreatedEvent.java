package com.btgpactual.desafio.listener.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class OrderCreatedEvent {

    @JsonProperty("codigoPedido")
    Long codigoPedido;

    @JsonProperty("codigoCliente")
    Long codigoCliente;

    @JsonProperty("items")
    List<OrderItemEvent>items;

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
        return items;
    }

    public void setItems(List<OrderItemEvent> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "codigoPedido=" + codigoPedido +
                ", codigoCliente=" + codigoCliente +
                ", itens=" + items +
                '}';
    }
}

