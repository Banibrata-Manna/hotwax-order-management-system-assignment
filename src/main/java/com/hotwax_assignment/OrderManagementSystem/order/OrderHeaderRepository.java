package com.hotwax_assignment.OrderManagementSystem.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Integer> {

    @Modifying
    @Query(value = "UPDATE order_header SET shipping_contact_mech_id = :shippingContactMechId WHERE order_id = :orderId", nativeQuery = true)
    public void updateShippingAddress(@Param("orderId") int orderId, @Param("shippingContactMechId") int shippingContactMechId);

    @Modifying
    @Query(value = "UPDATE order_header SET billing_contact_mech_id = :billingContactMechId WHERE order_id = :orderId", nativeQuery = true)
    public void updateBillingAddress(@Param("orderId") int orderId, @Param("billingContactMechId") int billingContactMechId);
}
//@Modifying
//@Query(value = "UPDATE order_header SET status = :status, modified_at = :modifiedAt WHERE id = :id", nativeQuery = true)
//void updateOrderHeaderDetails(@Param("status") String status, @Param("modifiedAt") LocalDateTime modifiedAt, @Param("id") Long id);
