package com.nimsoc.micro.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/invoices")
public class InvoiceController {

  @Autowired
  private InvoiceRepo invoiceRepo;

  @GetMapping
  @CrossOrigin(origins = "*")
  public ResponseEntity getOrders() {
    return ResponseEntity.ok().body(invoiceRepo.findAllInvoices());
  }
}
