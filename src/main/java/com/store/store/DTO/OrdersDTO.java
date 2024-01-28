package com.store.store.DTO;

import java.sql.Date;
import java.util.List;

import com.store.store.models.Customers;
import com.store.store.models.Items;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrdersDTO {

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    private Date order_date;

    private Double total_price;

    @NotNull(message = "Order code cannot be null")
    @Size(min = 1, message = "Order code must not be empty")
    private String order_code;

    @NotNull(message = "Customer code cannot be null")
    private Customers customer_code;

    @NotNull(message = "Item code cannot be null")
    private Items item_code;

    private List<ItemsDTO> items;
}