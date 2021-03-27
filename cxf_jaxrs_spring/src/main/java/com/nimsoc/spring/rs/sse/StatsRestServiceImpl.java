package com.nimsoc.spring.rs.sse;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.OutboundSseEvent.Builder;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import org.springframework.stereotype.Component;

@Path("/stats")
@Component
public class StatsRestServiceImpl {

  private static final Random RANDOM = new Random();
  private Sse sse;

  @Context
  public void setSse(Sse sse) {
    this.sse = sse;
  }

  @GET
  @Path("sse/{id}")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  public void stats(@Context SseEventSink sink, @PathParam("id") final String id) {
    new Thread() {
      @Override
      public void run() {

        try (sink) {
          final Builder builder = sse.newEventBuilder();
          sink.send(createStatsEvent(builder, 1));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 2));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 3));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 4));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 5));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 6));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 7));
          Thread.sleep(1000);
          sink.send(createStatsEvent(builder, 8));
        } catch (InterruptedException ex) {
          Logger.getLogger(StatsRestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

      }
    }.start();
  }

  private static OutboundSseEvent createStatsEvent(final OutboundSseEvent.Builder builder, final int eventId) {
    return builder
            .id("" + eventId)
            .data(Stats.class, new Stats(new Date().getTime(), RANDOM.nextInt(100)))
            .mediaType(MediaType.APPLICATION_JSON_TYPE)
            .build();
  }
}
