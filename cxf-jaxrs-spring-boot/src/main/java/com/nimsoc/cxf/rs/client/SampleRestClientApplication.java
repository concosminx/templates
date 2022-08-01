package com.nimsoc.cxf.rs.client;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.client.spring.EnableJaxRsProxyClient;
import org.apache.cxf.jaxrs.client.spring.EnableJaxRsWebClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.nimsoc.cxf.rs.service.api.HelloService;

@SpringBootApplication
@EnableJaxRsWebClient
@EnableJaxRsProxyClient
public class SampleRestClientApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(SampleRestClientApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
  }

  @Bean
  CommandLineRunner initWebClientRunner(final WebClient webClient) {
    return (String... runArgs) -> {
      System.out.println(webClient.path("sayHello/ApacheCxfWebClientUser").get(String.class));
    };
  }

  @Bean
  CommandLineRunner initProxyClientRunner(final HelloService client) {
    return (String... runArgs) -> {
      System.out.println(client.sayHello("ApacheCxfProxyUser"));
    };
  }
}
