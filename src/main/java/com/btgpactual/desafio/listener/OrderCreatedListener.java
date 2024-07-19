package com.btgpactual.desafio.listener;
import com.btgpactual.desafio.listener.dto.OrderCreatedEvent;
import com.btgpactual.desafio.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


import static com.btgpactual.desafio.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }


    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listar(OrderCreatedEvent message){
        logger.info("Message consumed: {}",message.toString());

        orderService.save(message);
    }

}
