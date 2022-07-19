package com.nimsoc.cxf.rs.service;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SampleRestApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SampleRestApplicationTest {

  @LocalServerPort
  private int port;

  @Test
  public void testHelloRequest() throws Exception {
    WebClient wc = WebClient.create("http://localhost:" + port + "/services/helloservice");
    wc.accept("text/plain");

    wc.path("sayHello").path("ApacheCxfUser");
    String greeting = wc.get(String.class);
    assertEquals("Hello ApacheCxfUser, Welcome to CXF RS Spring Boot World!!!", greeting);

    wc.back(true);

 
  }

}
