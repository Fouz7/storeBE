package com.store.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
}
