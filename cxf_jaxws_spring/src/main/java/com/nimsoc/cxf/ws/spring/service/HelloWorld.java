package com.nimsoc.cxf.ws.spring.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface HelloWorld {

  @WebResult(name = "greeting")
  String sayHello(@WebParam(name = "name") String text);
}
