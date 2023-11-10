package com.senyoudev.orderservice.controller;


import com.senyoudev.orderservice.dto.OrderRequest;
import com.senyoudev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Order placed for customer: {}", orderRequest.getOrderLineItemsDtos());
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
