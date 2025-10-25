package com.example.legacy.service;

import com.example.legacy.model.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    private final Map<String, Order> store = new ConcurrentHashMap<>();

    public Order create(Order o) {
        store.put(o.getId(), o);
        return o;
    }

    public Order findById(String id) {
        return store.get(id);
    }
}
