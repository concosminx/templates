package com.nimsoc.template.objects;

import java.io.Serializable;

public class Products implements Serializable {

  int id;
  String code;
  String description;
  double price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Products{" + "id=" + id + ", code=" + code + ", description=" + description + ", price=" + price + '}';
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

  

  
  
}
