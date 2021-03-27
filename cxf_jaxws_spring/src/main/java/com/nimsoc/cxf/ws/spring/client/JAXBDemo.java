package com.nimsoc.cxf.ws.spring.client;

import com.nimsoc.cxf.ws.spring.model.Person;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBDemo {

  public static void main(String[] args) {
    try {
      JAXBContext context = JAXBContext.newInstance(Person.class);
      Marshaller marshaller = context.createMarshaller();

      Person p = new Person();
      p.setName("John Doe");
      p.setId(981);

      StringWriter sw = new StringWriter();
      marshaller.marshal(p, sw);

      System.out.println("object -> xml " + sw.toString());

      Unmarshaller unmarshaller = context.createUnmarshaller();
      Person newPerson = (Person) unmarshaller.unmarshal(new StringReader(sw.toString()));

      System.out.println("xml -> object " + newPerson);

    } catch (JAXBException ex) {
      Logger.getLogger(JAXBDemo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
