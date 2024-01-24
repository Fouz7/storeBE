package com.store.store.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.DTO.OrdersDTO;
import com.store.store.models.Orders;
import com.store.store.services.OrdersService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/store/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public OrdersDTO createOrder(@Valid @RequestBody OrdersDTO orderDto) {
        return ordersService.createOrder(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrders(@PathVariable Long id, @Valid @RequestBody OrdersDTO orderDto){
        return ResponseEntity.ok(ordersService.updateOrders(id, orderDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
   
}

