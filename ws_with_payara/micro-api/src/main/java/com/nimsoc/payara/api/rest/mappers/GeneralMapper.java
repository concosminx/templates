package com.nimsoc.payara.api.rest.mappers;

import com.nimsoc.payara.lib.model.ApiError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class GeneralMapper implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception exception) {

    ApiError error = new ApiError();
    error.setRef(UUID.randomUUID());
    error.setStatus(500);
    error.setCode("internal.error");

    exception.printStackTrace();

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
  }
}
