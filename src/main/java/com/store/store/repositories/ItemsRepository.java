package com.store.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.models.Items;

public interface ItemsRepository extends JpaRepository<Items, Long>{
}
