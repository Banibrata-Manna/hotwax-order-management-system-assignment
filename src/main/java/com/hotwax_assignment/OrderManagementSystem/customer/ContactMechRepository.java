package com.hotwax_assignment.OrderManagementSystem.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMechRepository extends JpaRepository<ContactMech, Integer> {
}
