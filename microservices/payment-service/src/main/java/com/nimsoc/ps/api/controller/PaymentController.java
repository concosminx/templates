package com.nimsoc.ps.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimsoc.ps.api.model.Payment;
import com.nimsoc.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @PostMapping("/doPayment")
  public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
    return paymentService.doPayment(payment);
  }

  @GetMapping("/{orderId}")
  public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
    return paymentService.findPaymentHistoryByOrderId(orderId);
  }
}
