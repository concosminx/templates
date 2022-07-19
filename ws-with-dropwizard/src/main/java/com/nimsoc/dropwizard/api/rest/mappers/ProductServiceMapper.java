package com.nimsoc.dropwizard.api.rest.mappers;

import com.nimsoc.dropwizard.api.services.exceptions.ProductServiceException;
import com.nimsoc.dropwizard.lib.model.ApiError;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class ProductServiceMapper implements ExceptionMapper<ProductServiceException> {

  @Override
  public Response toResponse(ProductServiceException exception) {

    ApiError apiError = new ApiError();
    apiError.setRef(UUID.randomUUID());
    apiError.setStatus(400);
    apiError.setCode(exception.getCode().getCode());

    return Response.status(Response.Status.BAD_REQUEST).entity(apiError).build();
  }
}
