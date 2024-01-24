package com.store.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.store.DTO.CustomersDTO;
import com.store.store.models.Customers;
import com.store.store.repositories.CustomersRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomersService {
    private final CustomersRepository customersRepository;
    
    public CustomersDTO createCustomer(CustomersDTO customerDto) {
        Customers customer = convertToEntity(customerDto);
        return convertToDTO(customersRepository.save(customer));
    }

    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }   

    public Optional<Customers> getCustomerById(Long id) {
        return customersRepository.findById(id);
    }

    public Optional<Customers> findById(Long id) {
        return customersRepository.findById(id);
    }

    public Customers save(Customers customer) {
        return customersRepository.save(customer);
    }


    public CustomersDTO updateCustomer(Long id, CustomersDTO customerDto) {
        return customersRepository.findById(id)
            .map(c -> {
                c.setCustomer_name(customerDto.getCustomer_name());
                c.setCustomer_code(customerDto.getCustomer_code());
                c.setCustomer_address(customerDto.getCustomer_address());
                c.setCustomer_phone(customerDto.getCustomer_phone());
                c.setIs_active(customerDto.getIs_active());
                c.setLast_order_date(customerDto.getLast_order_date());
                c.setPic(customerDto.getPic());
                return convertToDTO(customersRepository.save(c));
            })
            .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public void deleteCustomer(Long id) {
        customersRepository.deleteById(id);
    }


    private CustomersDTO convertToDTO(Customers customer) {
        CustomersDTO customerDTO = new CustomersDTO();
        customerDTO.setCustomer_address(customer.getCustomer_address());
        customerDTO.setCustomer_code(customer.getCustomer_code());
        customerDTO.setCustomer_name(customer.getCustomer_name());
        customerDTO.setCustomer_phone(customer.getCustomer_phone());
        customerDTO.setIs_active(customer.getIs_active());
        customerDTO.setLast_order_date(customer.getLast_order_date());
        customerDTO.setPic(customer.getPic());
        return customerDTO;
    }
    
    private Customers convertToEntity(CustomersDTO customerDTO) {
        Customers customer = new Customers();
        customer.setCustomer_address(customerDTO.getCustomer_address());
        customer.setCustomer_code(customerDTO.getCustomer_code());
        customer.setCustomer_name(customerDTO.getCustomer_name());
        customer.setCustomer_phone(customerDTO.getCustomer_phone());
        customer.setIs_active(customerDTO.getIs_active());
        customer.setLast_order_date(customerDTO.getLast_order_date());
        customer.setPic(customerDTO.getPic());
        return customer;
    }

}
