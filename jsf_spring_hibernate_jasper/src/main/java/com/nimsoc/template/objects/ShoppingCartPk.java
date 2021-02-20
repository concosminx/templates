package com.nimsoc.template.objects;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingCartPk implements Serializable {

  String sessionId;
  int productId;

  public ShoppingCartPk() {
  }

  public ShoppingCartPk(String sessionId, int productId) {
    this.sessionId = sessionId;
    this.productId = productId;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + Objects.hashCode(this.sessionId);
    hash = 79 * hash + this.productId;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ShoppingCartPk other = (ShoppingCartPk) obj;
    if (this.productId != other.productId) {
      return false;
    }
    if (!Objects.equals(this.sessionId, other.sessionId)) {
      return false;
    }
    return true;
  }

}
