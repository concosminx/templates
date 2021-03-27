package com.nimsoc.cxf.rs.service;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.openapi.OpenApiFeature;
import org.apache.cxf.jaxrs.swagger.ui.SwaggerUiConfig;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nimsoc.cxf.rs.service.hello.HelloServiceImpl;

@SpringBootApplication
public class SampleRestApplication {

  @Autowired
  private Bus bus;
  @Autowired
  private MetricsProvider metricsProvider;

  public static void main(String[] args) {
    SpringApplication.run(SampleRestApplication.class, args);
  }

  @Bean
  public Server rsServer() {
    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
    endpoint.setBus(bus);
    endpoint.setServiceBeans(Arrays.<Object>asList(new HelloServiceImpl()));
    endpoint.setAddress("/");
    endpoint.setFeatures(Arrays.asList(createOpenApiFeature(), metricsFeature(), new LoggingFeature()));
    return endpoint.create();
  }

  @Bean
  public OpenApiFeature createOpenApiFeature() {
    final OpenApiFeature openApiFeature = new OpenApiFeature();
    openApiFeature.setPrettyPrint(true);
    openApiFeature.setTitle("Spring Boot CXF REST Application");
    openApiFeature.setContactName("N/A");
    openApiFeature.setDescription("This sample project demonstrates how to use CXF JAX-RS services with Spring Boot.");
    openApiFeature.setVersion("1.0.0");
    openApiFeature.setSwaggerUiConfig(
            new SwaggerUiConfig()
                    .url("/services/helloservice/openapi.json"));
    return openApiFeature;
  }

  @Bean
  public MetricsFeature metricsFeature() {
    return new MetricsFeature(metricsProvider);
  }
}
