package com.store.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.DTO.ItemsDTO;
import com.store.store.models.Items;
import com.store.store.services.ItemsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/store/items")
public class ItemsController {
    private final ItemsService itemsService;

    @GetMapping
    public ResponseEntity<List<Items>> getAllItems() {
        return ResponseEntity.ok(itemsService.getAllItems());
    }

    @PostMapping
    public ItemsDTO createItem(@Valid @RequestBody ItemsDTO itemsDto) {
        return itemsService.createItem(itemsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemsDTO> updateItems(@PathVariable Long id, @Valid @RequestBody ItemsDTO itemsDto) {
        return ResponseEntity.ok(itemsService.updateItems(id, itemsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        itemsService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    
}
