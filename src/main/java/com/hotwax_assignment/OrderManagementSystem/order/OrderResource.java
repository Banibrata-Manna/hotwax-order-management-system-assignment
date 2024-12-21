package com.hotwax_assignment.OrderManagementSystem.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class OrderResource {

    private OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderHeader> createOrder(@RequestBody OrderHeader orderHeader) {
        OrderHeader orderToBeCreated = orderService.createOrder(orderHeader);
        return new ResponseEntity<>(orderHeader, HttpStatus.CREATED);
    }

    @GetMapping("/orders/{order-id}")
    public ResponseEntity<OrderHeader> getOrder(@PathVariable("order-id") int orderId) {
        OrderHeader orderHeader = new OrderHeader();
        try{
            orderHeader = orderService.getOrderById(orderId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }
    @PutMapping("/orders/{order-id}")
    public ResponseEntity<OrderHeader> updateOrder(@PathVariable("order-id") int orderId, @RequestBody Map<String, String> detailsToUpdate) {
        OrderHeader updatedOrder = orderService.updateOrder(orderId, detailsToUpdate);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{order-id}")
    public ResponseEntity <String> deleteOrder(@PathVariable("order-id") int orderid) {
        orderService.deleteOrder(orderid);
        try {
            orderService.getOrderById(orderid);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<>("Success!", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("Deletion Failed!", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/orders/{order-id}/items")
    public ResponseEntity<OrderHeader> addItem(@PathVariable("order-id") int orderId, @RequestBody OrderItem orderItem) {
        OrderHeader updatedOrder = orderService.addOrderItem(orderId, orderItem);
        return new ResponseEntity<OrderHeader>(updatedOrder, HttpStatus.OK);
    }

    @PutMapping("/orders/{order-id}/items/{order-item-seq-id}")
    public ResponseEntity<OrderHeader> updateOrderItem(@PathVariable("order-id") int orderId
            , @PathVariable("order-item-seq-id") int orderItemSeqId, @RequestBody Map<String, String> orderItem) {
        OrderHeader orderHeader = orderService.updateOrderItem(orderId, orderItemSeqId, orderItem);
        return new ResponseEntity<>(orderHeader, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{order-id}/items/{order-item-seq-id}")
    public ResponseEntity<OrderHeader> deleteOrderItem(@PathVariable("order-id") int orderId
            , @PathVariable("order-item-seq-id") int orderItemSeqId) {


        return new ResponseEntity<>(orderService.deleteOrderItem
                        (orderId, orderItemSeqId)
                        , HttpStatus.OK);
    }
}
