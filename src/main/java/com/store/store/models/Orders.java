package com.store.store.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    private Double quantity;
    private Double total_price;
    private String order_code;
    private Date order_date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer_code;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item_code;

}
