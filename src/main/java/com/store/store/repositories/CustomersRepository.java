package com.store.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.models.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
    
}
