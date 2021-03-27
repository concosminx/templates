package com.nimsoc.spring.rs.sse;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public final class StatsClient {

  private StatsClient() {
  }

  public static void main(String[] args) throws InterruptedException {
    final WebTarget target = ClientBuilder
            .newClient()
            .register(JacksonJsonProvider.class)
            .target("http://localhost:8080/cxf_jaxrs_spring/rest/api/stats/sse/1");

    try (SseEventSource eventSource = SseEventSource.target(target).build()) {
      eventSource.register(StatsClient::print, System.out::println);
      eventSource.open();

      Thread.sleep(5000);
    }
  }

  private static void print(InboundSseEvent event) {
    final Stats stats = event.readData(Stats.class, MediaType.APPLICATION_JSON_TYPE);
    System.out.println(stats.getTimestamp() + ": " + stats.getLoad() + "%");
  }
}
