package com.nimsoc.cxf.ws.spring.service;

import com.nimsoc.cxf.ws.spring.model.PersonProcessorRequest;
import com.nimsoc.cxf.ws.spring.model.PersonProcessorResponse;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "PersonProcessor")
public interface PersonProcessor {

  @WebResult(name = "response")
  public PersonProcessorResponse processPerson(@WebParam(name = "personProcessorRequest") PersonProcessorRequest personProcessorRequest);
}
