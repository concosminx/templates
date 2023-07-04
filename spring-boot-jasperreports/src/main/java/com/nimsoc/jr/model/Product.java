package com.nimsoc.jr.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "product_type")
  @Enumerated(EnumType.STRING)
  private ProductType productType;

  public String getProductType() {
    return String.valueOf(productType);
  }

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "created_at")
  private LocalDate createdAt;

  public Product(String name,
                 String description,
                 ProductType productType,
                 BigDecimal price,
                 LocalDate createdAt) {
    this.name = name;
    this.description = description;
    this.productType = productType;
    this.price = price;
    this.createdAt = createdAt;
  }
}
