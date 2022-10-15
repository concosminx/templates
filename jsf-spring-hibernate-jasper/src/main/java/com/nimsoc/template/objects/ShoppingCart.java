package com.nimsoc.template.objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ShoppingCart")
@Table(name = "SHOPPING_CART")
public class ShoppingCart implements Serializable {

  private ShoppingCartPk pk;
  private double quantity;

  private String code;
  private String description;
  private double price;

  public ShoppingCart() {
  }

  public ShoppingCart(ShoppingCartPk pk, int quantity) {
    this.pk = pk;
    this.quantity = quantity;
  }

  @Id
  public ShoppingCartPk getPk() {
    return pk;
  }

  public void setPk(ShoppingCartPk pk) {
    this.pk = pk;
  }

  @Column(name = "QUANTITY", nullable = false)
  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  @Transient
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Transient
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Transient
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "ShoppingCart{" + "pk=" + pk + ", quantity=" + quantity + ", code=" + code + ", description=" + description + ", price=" + price + '}';
  }

}
