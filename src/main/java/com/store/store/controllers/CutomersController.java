package com.store.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.store.DTO.CustomersDTO;
import com.store.store.models.Customers;
import com.store.store.services.CustomersService;

import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/store/customers")
public class CutomersController {
    private final CustomersService customersServices;

    @GetMapping
    public ResponseEntity<List<Customers>> getAllCustomers() {
        return ResponseEntity.ok(customersServices.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable Long id) {
        return customersServices.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CustomersDTO createCustomer(@Valid @RequestBody CustomersDTO customerDto) {
        return customersServices.createCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomersDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomersDTO customerDto) {
        return ResponseEntity.ok(customersServices.updateCustomer(id, customerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customersServices.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}