package com.nimsoc.template.objects;

import java.io.Serializable;

public class ShoppingCart implements Serializable {

  ShoppingCartPk pk;
  double quantity;
  
  String code;
  String description;
  double price;

  public ShoppingCart() {
  }

  public ShoppingCart(ShoppingCartPk pk, int quantity) {
    this.pk = pk;
    this.quantity = quantity;
  }

  public ShoppingCartPk getPk() {
    return pk;
  }

  public void setPk(ShoppingCartPk pk) {
    this.pk = pk;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

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
