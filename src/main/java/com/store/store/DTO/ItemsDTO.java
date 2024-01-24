package com.store.store.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ItemsDTO {
    @NotNull(message = "is_available field is required")
    private Boolean is_available;

    @NotNull(message = "item_name field is required")
    @Size(min = 1, max = 50, message = "item_name must be between 1 and 50 characters")
    private String item_name;

    @NotNull(message = "item_code field is required")
    @Size(min = 1, max = 50, message = "item_code must be between 1 and 50 characters")
    private String item_code;

    @NotNull(message = "last_re_stock field is required")
    private Date last_re_stock;

    @NotNull(message = "price field is required")
    @Positive(message = "price must be a positive value")
    private Double price;

    @NotNull(message = "stock field is required")
    @Positive(message = "stock must be a positive value")
    private Double stock;
}
