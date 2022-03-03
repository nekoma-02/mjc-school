package com.epam.esm.controller;

import com.epam.esm.OrderService;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}")
    public List<Order> getOrderByUser(@PathVariable long userId, @Valid Pagination pagination) {
        List<Order> orderList = orderService.findByUserId(userId, pagination);
        return orderList;
    }

    @GetMapping("/{userId}/order/{orderId}")
    public Order getOrderByUser(@PathVariable long userId, @PathVariable long orderId) {
        return orderService.findByUserId(userId, orderId);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) {
        Order createdOrder = orderService.createOrder(order);
        ResponseEntity<Order> responseEntity = new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        return responseEntity;
    }
}
