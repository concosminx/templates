package com.nimsoc.payara.api.rest.filters;

import com.nimsoc.payara.lib.model.ApiError;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

@Provider
public class AuthFilter implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    String authHeader = requestContext.getHeaderString("Authorization");

    if (authHeader == null || authHeader.isEmpty()) {

      ApiError error = new ApiError();
      error.setRef(UUID.randomUUID());
      error.setStatus(401);
      error.setCode("unauthorized");

      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(error).build());
    }
  }
}
