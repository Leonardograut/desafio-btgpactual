package com.btgpactual.desafio.service;

import com.btgpactual.desafio.controller.dto.OrderResponse;
import com.btgpactual.desafio.entity.OrderEntity;
import com.btgpactual.desafio.entity.OrderItem;
import com.btgpactual.desafio.listener.dto.OrderCreatedEvent;
import com.btgpactual.desafio.listener.dto.OrderItemEvent;
import com.btgpactual.desafio.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;


    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
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


    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId){

        final Aggregation aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );

        final AggregationResults<Document> response = mongoTemplate.aggregate(aggregations,"tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest){
        final Page<OrderEntity> orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

}
