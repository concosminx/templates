package com.nimsoc.cxf.ws.spring.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.nimsoc.cxf.ws.spring.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

  @Override
  public String sayHello(String text) {
    return "Hello " + text;
  }
}
