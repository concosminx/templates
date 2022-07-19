package com.nimsoc.dropwizard.api.rest;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nimsoc.dropwizard.api.rest.auth.OpenIdAuthenticator;
import com.nimsoc.dropwizard.api.rest.auth.OpenIdAuthorizer;
import com.nimsoc.dropwizard.api.rest.auth.User;
import com.nimsoc.dropwizard.api.rest.mappers.EmptyPayloadMapper;
import com.nimsoc.dropwizard.api.rest.mappers.GeneralMapper;
import com.nimsoc.dropwizard.api.rest.mappers.IdMismatchMapper;
import com.nimsoc.dropwizard.api.rest.mappers.ProductServiceMapper;
import com.nimsoc.dropwizard.api.rest.mappers.ResourceNotFoundMapper;
import com.nimsoc.dropwizard.api.models.ProductDAO;
import com.nimsoc.dropwizard.api.models.db.ProductEntity;
import com.nimsoc.dropwizard.api.rest.resources.ProductResource;
import com.nimsoc.dropwizard.api.services.ProductService;
import com.nimsoc.dropwizard.api.services.impl.ProductServiceImpl;
import com.nimsoc.dropwizard.api.rest.resources.HelloWorldResource;
import com.nimsoc.dropwizard.api.rest.resources.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class MainApplication extends Application<MainConfiguration> {

  public static void main(String args[]) throws Exception {
    new MainApplication().run(args);
  }

  private final HibernateBundle<MainConfiguration> hibernate
          = new HibernateBundle<MainConfiguration>(ProductEntity.class) {

    @Override
    public DataSourceFactory getDataSourceFactory(MainConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  @Override
  public void initialize(Bootstrap<MainConfiguration> bootstrap) {
    bootstrap.addBundle(hibernate);
  }

  @Override
  public void run(MainConfiguration configuration, Environment environment) throws Exception {

    environment.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    environment.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    environment.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    environment.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    Client client = ClientBuilder.newClient();

    ProductService productService = new ProductServiceImpl(
            new ProductDAO(hibernate.getSessionFactory(), environment.metrics()),
            environment.metrics()
    );

    environment.jersey().register(new ProductResource(productService));

    final HelloWorldResource resource = new HelloWorldResource(
            "Hello, %s!",
            "Cosmin"
    );

    final TemplateHealthCheck healthCheck
            = new TemplateHealthCheck("Hello, %s!");

    environment.jersey().register(resource);
    environment.healthChecks().register("template", healthCheck);

    environment.jersey().register(EmptyPayloadMapper.class);
    environment.jersey().register(GeneralMapper.class);
    environment.jersey().register(IdMismatchMapper.class);
    environment.jersey().register(ResourceNotFoundMapper.class);
    environment.jersey().register(ProductServiceMapper.class);

    /*
      uncomment this section and provide a valid public key from IDP Server (Keycloack for example) 
      to test authentication and roles (config.yml - authPublicKey property)
      
      asign "admin" role to user in IDP
     
    /*
    environment.jersey().register(new AuthDynamicFeature(
            new OAuthCredentialAuthFilter.Builder<User>()
                    .setAuthenticator(new OpenIdAuthenticator(getAuthAlgorithm(configuration.getAuthPublicKey())))
                    .setAuthorizer(new OpenIdAuthorizer())
                    .setPrefix("Bearer")
                    .buildAuthFilter()));
    environment.jersey().register(RolesAllowedDynamicFeature.class);
    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    */
     
  }

  private Algorithm getAuthAlgorithm(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyFactory kf = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
    RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
    return Algorithm.RSA256(pubKey, null);
  }
}
