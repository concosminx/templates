package com.acme.payments.api.services.exceptions;

public class GeneralServiceException extends RuntimeException {

  public GeneralServiceException(Throwable cause) {
    super(cause);
  }
}
