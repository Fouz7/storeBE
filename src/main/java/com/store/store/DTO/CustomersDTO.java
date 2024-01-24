package com.store.store.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CustomersDTO {
    
    @NotBlank(message = "Customer code is required")
    @Size(max = 50, message = "Customer code must be at most 50 characters")
    private String customer_code;

    @NotBlank(message = "Customer name is required")
    @Size(max = 50, message = "Customer name must be at most 50 characters")
    private String customer_name;

    @NotBlank(message = "Customer address is required")
    @Size(max = 50, message = "Customer address must be at most 50 characters")
    private String customer_address;

    @NotBlank(message = "Customer phone is required")
    @Pattern(regexp = "\\d{11,13}", message = "Customer phone must be a valid number with 11 to 13 digits")
    private String customer_phone;

    @NotNull(message = "Is active flag is required")
    private Boolean is_active;

    private Date last_order_date;

    @NotBlank(message = "PIC is required")
    private String pic;
}