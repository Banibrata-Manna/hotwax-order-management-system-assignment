package com.hotwax_assignment.OrderManagementSystem.order;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class OrderHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(nullable = false)
    private Date orderDate;
    private int customerId;
    private int shippingContactMechId;
    private int billingContactMechId;
    @OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> listOfItems;

    public List<OrderItem> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<OrderItem> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getShippingContactMechId() {
        return shippingContactMechId;
    }

    public void setShippingContactMechId(int shippingContactMechId) {
        this.shippingContactMechId = shippingContactMechId;
    }

    public int getBillingContactMechId() {
        return billingContactMechId;
    }

    public void setBillingContactMechId(int billingContactMechId) {
        this.billingContactMechId = billingContactMechId;
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customerId=" + customerId +
                ", shippingContactMechId=" + shippingContactMechId +
                ", billingContactMechId=" + billingContactMechId +
                ", listOfItems=" + listOfItems +
                '}';
    }
}
