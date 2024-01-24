package com.store.store.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.store.DTO.OrdersDTO;
import com.store.store.models.Customers;
import com.store.store.models.Items;
import com.store.store.models.Orders;
import com.store.store.repositories.ItemsRepository;
import com.store.store.repositories.OrdersRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomersService customerRepository;
    private final ItemsRepository itemRepository;

    public OrdersDTO createOrder(OrdersDTO orderDto) {
        Optional<Items> item = itemRepository.findById(orderDto.getItem_code().getItem_id());
        if (item.isPresent()) {
            orderDto.setTotal_price(item.get().getPrice() * orderDto.getQuantity());
            Items selectedItem = item.get();
            selectedItem.setStock(selectedItem.getStock() - orderDto.getQuantity());
        }

        Orders order = convertToEntity(orderDto);
        order.setOrder_date(Date.valueOf(LocalDate.now()));
        OrdersDTO savedOrder = convertToDTO(ordersRepository.save(order));

        Optional<Customers> customer = customerRepository.findById(savedOrder.getCustomer_code().getCustomer_id());
        if (customer.isPresent()){
            Customers updatedCustomer = customer.get();
            updatedCustomer.setLast_order_date(savedOrder.getOrder_date());
            updatedCustomer.setIs_active(true);
            customerRepository.save(updatedCustomer);
        }

        return savedOrder;
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id) {
        return ordersRepository.findById(id);
    }


    public OrdersDTO updateOrders(Long id, OrdersDTO orderDto){
        return ordersRepository.findById(id)
            .map(o -> {
                o.setOrder_date(orderDto.getOrder_date());
                o.setOrder_code(orderDto.getOrder_code());
                o.setTotal_price(orderDto.getTotal_price());
                o.setQuantity(orderDto.getQuantity());
                o.setCustomer_code(orderDto.getCustomer_code());
                o.setItem_code(orderDto.getItem_code());

                Optional<Items> item = itemRepository.findById(orderDto.getItem_code().getItem_id());
                if (item.isPresent()) {
                    o.setTotal_price(item.get().getPrice() * orderDto.getQuantity());
                    Items selectedItem = item.get();
                    selectedItem.setStock(selectedItem.getStock() - orderDto.getQuantity());
                }

                Optional<Customers> customer = customerRepository.findById(o.getCustomer_code().getCustomer_id());
                if (customer.isPresent()){
                    Customers updatedCustomer = customer.get();
                    updatedCustomer.setLast_order_date(orderDto.getOrder_date());
                    updatedCustomer.setIs_active(true);
                    customerRepository.save(updatedCustomer);
                }

                return convertToDTO(ordersRepository.save(o));
            })
            .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }



    private OrdersDTO convertToDTO(Orders order) {
        OrdersDTO orderDTO = new OrdersDTO();
        orderDTO.setOrder_date(order.getOrder_date());
        orderDTO.setOrder_code(order.getOrder_code());
        orderDTO.setTotal_price(order.getTotal_price());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setCustomer_code(order.getCustomer_code());
        orderDTO.setItem_code(order.getItem_code());

        return orderDTO;
    }

    private Orders convertToEntity(OrdersDTO orderDTO) {
        Orders order = new Orders();
        order.setOrder_date(orderDTO.getOrder_date());
        order.setOrder_code(orderDTO.getOrder_code());
        order.setQuantity(orderDTO.getQuantity());
        order.setCustomer_code(orderDTO.getCustomer_code());
        order.setItem_code(orderDTO.getItem_code());
        order.setTotal_price(orderDTO.getTotal_price());

        return order;
    }
}
