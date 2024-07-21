package com.btgpactual.desafio.service;

import com.btgpactual.desafio.controller.dto.OrderResponse;
import com.btgpactual.desafio.entity.OrderEntity;
import com.btgpactual.desafio.entity.OrderItem;
import com.btgpactual.desafio.listener.dto.OrderCreatedEvent;
import com.btgpactual.desafio.listener.dto.OrderItemEvent;
import com.btgpactual.desafio.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;



    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public void save (OrderCreatedEvent event){

        OrderEntity entity = new OrderEntity();

        entity.setOrderId(event.getCodigoPedido());
        entity.setCustomerId(event.getCodigoCliente());

        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);

    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
           BigDecimal total = BigDecimal.ZERO;

           for(OrderItemEvent orderItemEvent : event.getItems()){
               BigDecimal itemTotal = orderItemEvent.getPreco().multiply(BigDecimal.valueOf(orderItemEvent.getQuantidade()));
               total = total.add(itemTotal);
           }
           if (total.equals(BigDecimal.ZERO)){
               return BigDecimal.ZERO;
           }
           return total;
    }

    private static List<OrderItem>getOrderItems(OrderCreatedEvent event) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemEvent items : event.getItems()) {

            orderItems.add(new OrderItem(items.getProduto(), items.getQuantidade(), items.getPreco()));
        }

        return orderItems;
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest){
        final Page<OrderEntity> orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

}
