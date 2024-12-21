package com.hotwax_assignment.OrderManagementSystem.order;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemSeqId;
    private int orderId;
    private int productId;
    @Column(nullable = false)
    private int quantity;
    @Column(length = 20, nullable = false)
    private String status;

    public int getOrderItemSeqId() {
        return orderItemSeqId;
    }

    public void setOrderItemSeqId(int orderItemSeqId) {
        this.orderItemSeqId = orderItemSeqId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemSeqId=" + orderItemSeqId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
