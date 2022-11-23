package com.nimsoc.os.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimsoc.os.api.common.Payment;
import com.nimsoc.os.api.common.TransactionRequest;
import com.nimsoc.os.api.common.TransactionResponse;
import com.nimsoc.os.api.model.Order;
import com.nimsoc.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping("/bookOrder")
  public TransactionResponse bookOrder(@RequestBody TransactionRequest tr) throws JsonProcessingException {
    return orderService.saveOrder(tr);
  }
}
