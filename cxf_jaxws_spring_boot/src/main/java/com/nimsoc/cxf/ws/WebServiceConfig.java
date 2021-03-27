package com.nimsoc.cxf.ws;

import com.nimsoc.cxf.ws.service.FileWsImpl;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;

import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.nimsoc.cxf.ws.service.HelloPortImpl;
import javax.xml.ws.soap.SOAPBinding;

@Configuration
public class WebServiceConfig {

  @Autowired
  private Bus bus;

  @Autowired
  private MetricsProvider metricsProvider;

  @Bean
  public Endpoint endpoint() {
    EndpointImpl endpoint = new EndpointImpl(bus, new HelloPortImpl(), null, null, new MetricsFeature[]{
      new MetricsFeature(metricsProvider)
    });
    endpoint.publish("/Hello");
    return endpoint;
  }

  @Bean
  public Endpoint endpointMtom() {
    EndpointImpl endpoint = new EndpointImpl(bus, new FileWsImpl());
    endpoint.publish("/fileWs");

    SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
    binding.setMTOMEnabled(true);
    return endpoint;

  }
}
