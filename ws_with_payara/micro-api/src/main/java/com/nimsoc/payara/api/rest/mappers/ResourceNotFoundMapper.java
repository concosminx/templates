package com.nimsoc.payara.api.rest.mappers;

import com.nimsoc.payara.api.rest.services.exceptions.ResourceNotFoundException;
import com.nimsoc.payara.lib.model.ApiError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<ResourceNotFoundException> {

  @Override
  public Response toResponse(ResourceNotFoundException exception) {

    ApiError error = new ApiError();
    error.setRef(UUID.randomUUID());
    error.setStatus(404);
    error.setCode("resource.not.found");

    return Response.status(Response.Status.NOT_FOUND).entity(error).build();
  }
}
