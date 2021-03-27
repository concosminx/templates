package com.nimsoc.jaxrs.lib.models;

public class Person extends BaseModel {
  private String name;
  private int age; 
  private Gender gender;

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Gender getGender() {
    return gender;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }
}
