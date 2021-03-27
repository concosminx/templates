package com.nimsoc.cxf.ws.spring.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
  "name",
  "dob",
  "age",
  "emails"
})
@XmlRootElement(name = "person")
public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  @XmlElement(required = true)
  private String name;

  private int age;

  @XmlElement(required = true)
  @XmlSchemaType(name = "date")
  private XMLGregorianCalendar dob;

  @XmlElement(name = "email", required = true)
  private List<String> emails;

  @XmlAttribute(name = "id")
  private Integer id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public XMLGregorianCalendar getDob() {
    return dob;
  }

  public void setDob(XMLGregorianCalendar dob) {
    this.dob = dob;
  }

  public List<String> getEmails() {
    return emails;
  }

  public void setEmails(List<String> emails) {
    this.emails = emails;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Person{" + "name=" + name + ", age=" + age + ", dob=" + dob + ", emails=" + emails + ", id=" + id + '}';
  }

}
