package com.nimsoc.os.api.common;


import com.nimsoc.os.api.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

  private Order order;
  private String transactionId;
  private double ammount;
}
