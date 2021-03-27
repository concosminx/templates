package com.nimsoc.dropwizard.lib.model;

public enum ProductServiceErrorCode {

  UNKNOWN("unknown");

  private final String code;

  ProductServiceErrorCode(String code) {
    this.code = code;
  }

  public static ProductServiceErrorCode findByCode(String code) {
    for (ProductServiceErrorCode errorCode : values()) {
      if (errorCode.getCode().equals(code)) {
        return errorCode;
      }
    }

    return UNKNOWN;
  }

  public String getCode() {
    return code;
  }
}
