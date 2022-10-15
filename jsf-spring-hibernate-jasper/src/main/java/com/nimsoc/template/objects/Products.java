package com.nimsoc.template.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "Products")
@Table(name = "PRODUCTS")
public class Products implements Serializable {

  @Column(name = "ID", nullable=false)
  private int id;
  private String code;
  private String description;
  private double price;

  @Id
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "CODE", nullable=false)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "DESCRIPTION", nullable=false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "PRICE", nullable=false)
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Products{" + "id=" + id + ", code=" + code + ", description=" + description + ", price=" + price + '}';
  }

  
  
}
