package com.nimsoc.micro.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/partners")
public class PartnerController {

  @Autowired
  private PartnerRepo partnerRepo;

  @GetMapping
  @CrossOrigin(origins = "*")
  public ResponseEntity getOrders() {
    return ResponseEntity.ok().body(partnerRepo.findAllPartners());
  }

}
