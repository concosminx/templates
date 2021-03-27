package com.nimsoc.payara.api.rest.services.exceptions;

public class EmptyPayloadException extends RuntimeException {

  private String resource;

  public EmptyPayloadException(String resource) {
    this.resource = resource;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }
}
