package com.nimsoc.dropwizard.api.rest.mappers;

import com.nimsoc.dropwizard.api.services.exceptions.IdMismatchException;
import com.nimsoc.dropwizard.lib.model.ApiError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class IdMismatchMapper implements ExceptionMapper<IdMismatchException> {

  @Override
  public Response toResponse(IdMismatchException exception) {

    ApiError error = new ApiError();
    error.setRef(UUID.randomUUID());
    error.setStatus(400);
    error.setCode("resource.id.mismatch");

    return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
  }
}
