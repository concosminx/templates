package com.nimsoc.micro.partner;

public class Partner {
  private String code;
  private String description;
  private String email;

  public Partner() {
  }

  public Partner(String code, String description, String email) {
    this.code = code;
    this.description = description;
    this.email = email;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Partner{" + "code=" + code + ", description=" + description + ", email=" + email + '}';
  }
  
  
}
