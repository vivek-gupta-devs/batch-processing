package com.learning.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
public class Product {

    @Id
    private int productId;
    private String title;
    private String description;
    private String price;
    private String discount;
    private String discountedPrice;


}
