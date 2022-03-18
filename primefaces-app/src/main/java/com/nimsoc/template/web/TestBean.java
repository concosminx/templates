package com.nimsoc.template.web;

//https://github.com/primefaces/primefaces

import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Cosmin
 */

@Named(value = "testBean")
@SessionScoped
public class TestBean implements java.io.Serializable {
  private double number; 
  private Date date;

  public double getNumber() {
    return number;
  }

  public void setNumber(double number) {
    this.number = number;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
  
  public String doAction() {
    return "show-test-date";
  }
}
