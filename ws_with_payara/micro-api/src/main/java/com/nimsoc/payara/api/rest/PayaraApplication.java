package com.nimsoc.payara.api.rest;

import com.nimsoc.payara.api.rest.filters.AuthFilter;
import com.nimsoc.payara.api.rest.filters.CorsFilter;
import com.nimsoc.payara.api.rest.filters.LoggerInterceptor;
import com.nimsoc.payara.api.rest.mappers.EmptyPayloadMapper;
import com.nimsoc.payara.api.rest.mappers.ResourceNotFoundMapper;
import com.nimsoc.payara.api.rest.resources.ProductCategoryResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;

@ApplicationPath("/")
public class PayaraApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {

    Set<Class<?>> classes = new HashSet<>();

    
    classes.add(JsonProcessingFeature.class);
    classes.add(ProductCategoryResource.class);

    classes.add(EmptyPayloadMapper.class);
    classes.add(ResourceNotFoundMapper.class);

    classes.add(AuthFilter.class);
    classes.add(CorsFilter.class);
    classes.add(LoggerInterceptor.class);

    return classes;

  }
}
