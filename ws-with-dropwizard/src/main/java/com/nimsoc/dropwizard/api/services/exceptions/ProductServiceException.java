package com.nimsoc.dropwizard.api.services.exceptions;

import com.nimsoc.dropwizard.lib.model.ProductServiceErrorCode;

public class ProductServiceException extends RuntimeException {

  private ProductServiceErrorCode code;

  public ProductServiceException(ProductServiceErrorCode code) {
    this.code = code;
  }

  public ProductServiceErrorCode getCode() {
    return code;
  }
}
