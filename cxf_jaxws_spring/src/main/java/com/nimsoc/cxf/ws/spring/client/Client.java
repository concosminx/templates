package com.nimsoc.cxf.ws.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nimsoc.cxf.ws.spring.service.HelloWorld;

public final class Client {

  private Client() {
  }

  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"client-beans.xml"});
    HelloWorld client = (HelloWorld) context.getBean("client");
    String response = client.sayHello("Stranger");
    System.out.println("Response: " + response);
  }
}
