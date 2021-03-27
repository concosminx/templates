package com.nimsoc.cxf.ws.spring.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PersonProcessorRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonProcessorRequest {

  @XmlElement(name = "person", required = true)
  private Person person;
  @XmlAttribute(name = "newAge", required = true)
  private int newAge;

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public int getNewAge() {
    return newAge;
  }

  public void setNewAge(int newAge) {
    this.newAge = newAge;
  }

  @Override
  public String toString() {
    return "PersonProcessorRequest{" + "person=" + person + ", age=" + newAge + '}';
  }

}
