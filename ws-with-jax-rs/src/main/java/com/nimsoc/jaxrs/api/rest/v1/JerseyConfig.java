package com.nimsoc.jaxrs.api.rest.v1;

import com.nimsoc.jaxrs.api.rest.v1.mappers.EmptyPayloadMapper;
import com.nimsoc.jaxrs.api.rest.v1.mappers.GeneralMapper;
import com.nimsoc.jaxrs.api.rest.v1.mappers.IdMismatchMapper;
import com.nimsoc.jaxrs.api.rest.v1.mappers.ResourceNotFoundMapper;
import com.nimsoc.jaxrs.api.rest.v1.resources.PersonResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(PersonResource.class);

    register(EmptyPayloadMapper.class);
    register(GeneralMapper.class);
    register(IdMismatchMapper.class);
    register(ResourceNotFoundMapper.class);
  }
}
