package com.nimsoc.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimsoc.ps.api.model.Payment;
import com.nimsoc.ps.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

  Logger logger= LoggerFactory.getLogger(PaymentService.class);

  @Autowired
  PaymentRepository repository;

  public Payment doPayment(Payment payment) throws JsonProcessingException {
    payment.setPaymentStatus(paymentProcessing());
    payment.setTransactionId(UUID.randomUUID().toString());
    logger.info("Payment-Service Request : {}",new ObjectMapper().writeValueAsString(payment));

    return repository.save(payment);
  }

  //simulate success or failure
  public String paymentProcessing(){
    return new Random().nextBoolean()?"success":"failure";
  }

  public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
    Payment payment=repository.findByOrderId(orderId);
    logger.info("Payment-Service findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
    return payment ;
  }
}
