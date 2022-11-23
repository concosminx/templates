package com.nimsoc.os.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimsoc.os.api.common.Payment;
import com.nimsoc.os.api.common.TransactionRequest;
import com.nimsoc.os.api.common.TransactionResponse;
import com.nimsoc.os.api.model.Order;
import com.nimsoc.os.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

  Logger logger= LoggerFactory.getLogger(OrderService.class);

  @Autowired
  private OrderRepository repository;

  @Autowired
  @Lazy
  private RestTemplate restTemplate;

  @Value("${microservice.payment-service.endpoints.endpoint.uri}")
  private String PAYMENT_API_ENDPOINT_URL;

  public TransactionResponse saveOrder(TransactionRequest tr) throws JsonProcessingException {
    Order order = tr.getOrder();
    repository.save(order);

    Payment payment = tr.getPayment();
    payment.setOrderId(order.getId());
    payment.setAmount(order.getPrice());

    logger.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(tr));
    Payment paymentResponse = restTemplate.postForObject(PAYMENT_API_ENDPOINT_URL, payment, Payment.class);
    logger.info("Order-Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(paymentResponse));
    return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount());
  }

}
