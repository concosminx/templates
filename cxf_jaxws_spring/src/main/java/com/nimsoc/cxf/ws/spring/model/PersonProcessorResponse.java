package com.nimsoc.cxf.ws.spring.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PersonProcessorResponse")
public class PersonProcessorResponse {

  private boolean result;

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "PersonProcessorResponse{" + "result=" + result + '}';
  }

}
