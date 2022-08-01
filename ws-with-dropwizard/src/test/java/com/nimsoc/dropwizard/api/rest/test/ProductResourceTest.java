package com.nimsoc.dropwizard.api.rest.test;

import com.nimsoc.dropwizard.api.rest.providers.JacksonProvider;
import com.nimsoc.dropwizard.api.rest.MainApplication;
import com.nimsoc.dropwizard.api.rest.MainConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductResourceTest {

  //also read: https://www.dropwizard.io/en/latest/manual/testing.html
  
  @ClassRule
  public static final DropwizardAppRule<MainConfiguration> RULE
          = new DropwizardAppRule<>(MainApplication.class, ResourceHelpers.resourceFilePath("config.yml"));

  @Test
  public void testFindProducts() {

    Client client = ClientBuilder.newClient().register(JacksonProvider.class);

    Response response = client.target(
            String.format("http://localhost:%d/products", RULE.getLocalPort()))
            .request()
            .get();

    assertThat(response.getStatus()).isEqualTo(200);
  }
}
