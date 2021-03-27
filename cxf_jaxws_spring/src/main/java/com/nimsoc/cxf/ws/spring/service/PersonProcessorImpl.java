package com.nimsoc.cxf.ws.spring.service;

import com.nimsoc.cxf.ws.spring.model.PersonProcessorRequest;
import com.nimsoc.cxf.ws.spring.model.PersonProcessorResponse;

public class PersonProcessorImpl implements PersonProcessor {

  @Override
  public PersonProcessorResponse processPerson(PersonProcessorRequest rersonProcessorRequest) {
    PersonProcessorResponse response = new PersonProcessorResponse();

    response.setResult(true);
    return response;
  }
}
