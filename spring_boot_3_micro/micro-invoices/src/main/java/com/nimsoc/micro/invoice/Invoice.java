package com.nimsoc.micro.invoice;

import java.math.BigDecimal;

public class Invoice {

  private String id;
  private InvoiceStatus status;
  private BigDecimal totalPrice;
  private Integer numberOfItems;
  private String transactionId;
  private String cardType;
  private String cardLast4;

  public Invoice() {
  }

  public Invoice(String id, InvoiceStatus status, BigDecimal totalPrice, Integer numberOfItems, String transactionId) {
    this.id = id;
    this.status = status;
    this.totalPrice = totalPrice;
    this.numberOfItems = numberOfItems;
    this.transactionId = transactionId;
  }
  
  public Invoice(Invoice other) {
    this.id = other.getId();
    this.status = other.getStatus();
    this.totalPrice = other.getTotalPrice();
    this.numberOfItems = other.getNumberOfItems();
    this.transactionId = other.getTransactionId();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InvoiceStatus getStatus() {
    return status;
  }

  public void setStatus(InvoiceStatus status) {
    this.status = status;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Integer getNumberOfItems() {
    return numberOfItems;
  }

  public void setNumberOfItems(Integer numberOfItems) {
    this.numberOfItems = numberOfItems;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getCardLast4() {
    return cardLast4;
  }

  public void setCardLast4(String cardLast4) {
    this.cardLast4 = cardLast4;
  }

  @Override
  public String toString() {
    return "Invoice{" + "id=" + id + ", status=" + status + ", totalPrice=" + totalPrice + ", numberOfItems=" + numberOfItems + ", transactionId=" + transactionId + ", cardType=" + cardType + ", cardLast4=" + cardLast4 + '}';
  }

}
