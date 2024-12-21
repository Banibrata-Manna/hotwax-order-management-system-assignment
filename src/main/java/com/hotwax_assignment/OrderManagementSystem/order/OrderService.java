package com.hotwax_assignment.OrderManagementSystem.order;

import com.hotwax_assignment.OrderManagementSystem.customer.ContactMech;
import com.hotwax_assignment.OrderManagementSystem.customer.ContactMechRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private OrderHeaderRepository orderHeaderRepository;
    private OrderItemRepository orderItemRepository;
    private ContactMechRepository contactMechRepository;

    public OrderService(OrderHeaderRepository orderHeaderRepository, OrderItemRepository orderItemRepository, ContactMechRepository contactMechRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.orderItemRepository = orderItemRepository;
        this.contactMechRepository = contactMechRepository;
    }

    @Transactional
    public OrderHeader createOrder(OrderHeader orderHeader) {
        List<OrderItem> list = orderHeader.getListOfItems();
        orderHeader.setListOfItems(null);
        OrderHeader savedOrderHeader = orderHeaderRepository.save(orderHeader);
        savedOrderHeader.setListOfItems(list);
        for(OrderItem item : list) {
            item.setOrderId(savedOrderHeader.getOrderId());
        }
        return orderHeaderRepository.save(savedOrderHeader);
    }

    public OrderHeader getOrderById(int id) {

        return orderHeaderRepository.findById(id).get();
    }

    @Transactional
    public OrderHeader updateOrder(int orderId, Map<String, String> detailsToUpdate) {

        if(detailsToUpdate.containsKey("shippingContactMechId") && detailsToUpdate.containsKey("streetAddress")) {
            ContactMech updatedContactMech =  contactMechRepository.findById(
                    Integer.parseInt(
                            detailsToUpdate.get("shippingContactMechId"))).get();
            updatedContactMech.setStreetAddress(detailsToUpdate.get("streetAddress"));
            contactMechRepository.save(updatedContactMech);
            return getOrderById(orderId);
        }

        if(detailsToUpdate.containsKey("billingContactMechId") && detailsToUpdate.containsKey("streetAddress")) {
            ContactMech updatedContactMech =  contactMechRepository.findById(
                    Integer.parseInt(
                            detailsToUpdate.get("billingContactMechId"))).get();
            updatedContactMech.setStreetAddress(detailsToUpdate.get("streetAddress"));
            contactMechRepository.save(updatedContactMech);
            return getOrderById(orderId);
        }

        if(detailsToUpdate.containsKey("shippingContactMechId")){
            orderHeaderRepository.updateShippingAddress(orderId,
                    Integer.parseInt(
                            detailsToUpdate.get("shippingContactMechId")));
            return getOrderById(orderId);
        }

        if(detailsToUpdate.containsKey("billingContactMechId")) {
            orderHeaderRepository.updateBillingAddress(orderId,
                    Integer.parseInt(
                            detailsToUpdate.get("billingContactMechId")));
            return getOrderById(orderId);
        }
        return null;
    }

    @Transactional
    public void deleteOrder(int orderid) {
        orderHeaderRepository.deleteById(orderid);
    }

    @Transactional
    public OrderHeader addOrderItem(int orderId, OrderItem newOrderItem) {
        OrderHeader orderToBeUpdated = getOrderById(orderId);
        newOrderItem.setOrderId(orderId);
        orderToBeUpdated.getListOfItems().add(newOrderItem);
        return orderHeaderRepository.save(orderToBeUpdated);
    }

    @Transactional
    public OrderHeader updateOrderItem(int orderId
            , int orderItemSeqId, Map<String, String> newOrderItemDetails) {
        OrderItem orderItemToBeUpdated = orderItemRepository.findById(orderItemSeqId).get();
        if(newOrderItemDetails.containsKey("quantity")) {
            orderItemToBeUpdated.setQuantity(Integer.parseInt(newOrderItemDetails.get("quantity")));
        }

        if(newOrderItemDetails.containsKey("status")) {
            orderItemToBeUpdated.setStatus(newOrderItemDetails.get("status"));
        }

        orderItemRepository.save(orderItemToBeUpdated);

        return getOrderById(orderId);
    }

    public OrderHeader deleteOrderItem(int orderId, int orderItemSeqId) {
        orderItemRepository.deleteById(orderItemSeqId);
        return orderHeaderRepository.findById(orderId).get();
    }
}
