package com.example.legacy.controller;

import com.example.legacy.config.AppSettings;
import com.example.legacy.dto.OrderDto;
import com.example.legacy.model.Order;
import com.example.legacy.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final AppSettings settings;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService, AppSettings settings) {
        this.orderService = orderService;
        this.settings = settings;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDto dto) {
        logger.info("Create order request: customer={} itemsCount={} env={}", dto.getCustomerName(), dto.getItems().size(), settings.getEnvironment());
        Order o = new Order();
        o.setCustomerName(dto.getCustomerName());
        o.setItems(dto.getItems());
        o.setTotal(dto.getTotal());
        Order saved = orderService.create(o);
        logger.info("Order created id={}", saved.getId());
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        logger.info("Get order id={}", id);
        Order o = orderService.findById(id);
        if (o == null) {
            logger.warn("Order not found id={}", id);
            return ResponseEntity.status(404).body("Order not found");
        }
        return ResponseEntity.ok(o);
    }
}
