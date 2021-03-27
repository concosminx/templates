package com.nimsoc.micro.partner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class PartnerRepo {
  
  private final List<Partner> allPartners = new ArrayList<>();
  
  @PostConstruct
  private void init() {
    allPartners.add(new Partner(UUID.randomUUID().toString(), "My 1st Company", "e1@mycompanies.com"));
    allPartners.add(new Partner(UUID.randomUUID().toString(), "My 2nd Company", "e2@mycompanies.com"));
    allPartners.add(new Partner(UUID.randomUUID().toString(), "My 3rd Company", "e3@mycompanies.com"));
  }
  
  public List<Partner> findAllPartners() {
    return allPartners;
  }
  
}
