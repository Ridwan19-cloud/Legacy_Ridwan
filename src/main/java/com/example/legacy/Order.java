package com.example.legacy.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private String customerName;
    private Instant createdAt;
    private List<String> items;
    private double total;

    public Order() { this.id = UUID.randomUUID().toString(); this.createdAt = Instant.now(); }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
