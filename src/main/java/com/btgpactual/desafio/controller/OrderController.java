package com.btgpactual.desafio.controller;

import com.btgpactual.desafio.controller.dto.ApiResponse;
import com.btgpactual.desafio.controller.dto.OrderResponse;
import com.btgpactual.desafio.controller.dto.PaginationResponse;
import com.btgpactual.desafio.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


@RestController
public class OrderController {

    private  final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>>listOrders(@PathVariable("customerId") Long customerId,
                                                                @RequestParam(name ="page",defaultValue = "0")Integer page,
                                                                @RequestParam(name ="pageSize",defaultValue = "10")Integer pageSize){

        final Page<OrderResponse> body = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));

        final BigDecimal totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                 Map.of("totalOnOrders",totalOnOrders),
                 body.getContent(),new PaginationResponse(body.getNumber(),
                                        body.getSize(),
                                        body.getTotalPages(),
                                        body.getTotalElements())
        ));
    }
}
