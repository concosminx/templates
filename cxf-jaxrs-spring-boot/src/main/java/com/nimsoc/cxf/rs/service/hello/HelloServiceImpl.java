package com.nimsoc.cxf.rs.service.hello;

import javax.ws.rs.Path;

import com.nimsoc.cxf.rs.service.api.HelloService;

@Path("/sayHello")
public class HelloServiceImpl implements HelloService {

  @Override
  public String sayHello(String a) {
    return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
  }

}
