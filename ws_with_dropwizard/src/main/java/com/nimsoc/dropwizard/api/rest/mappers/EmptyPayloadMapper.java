package com.nimsoc.dropwizard.api.rest.mappers;

import com.nimsoc.dropwizard.api.services.exceptions.EmptyPayloadException;
import com.nimsoc.dropwizard.lib.model.ApiError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class EmptyPayloadMapper implements ExceptionMapper<EmptyPayloadException> {

  @Override
  public Response toResponse(EmptyPayloadException exception) {

    ApiError error = new ApiError();
    error.setRef(UUID.randomUUID());
    error.setStatus(400);
    error.setCode("resource.empty.payload");

    return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
  }
}
