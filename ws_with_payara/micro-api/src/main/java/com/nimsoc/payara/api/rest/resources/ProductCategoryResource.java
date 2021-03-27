package com.nimsoc.payara.api.rest.resources;

import com.nimsoc.payara.api.rest.services.ProductCategoryService;
import com.nimsoc.payara.lib.model.ProductCategory;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductCategoryResource {

  @Inject
  ProductCategoryService productCategoryService;

  @GET
  public Response getCategories(@QueryParam("limit") Integer limit,
          @QueryParam("offset") Integer offset) throws SQLException {

    return Response.ok(productCategoryService.findProductCategories(limit, offset))
            .header("X-Total-Count", 0).build();
  }

  @GET
  @Path("/{id}")
  public Response getCategory(@PathParam("id") String id) throws SQLException {

    return Response.ok(productCategoryService.findProductCategoryById(id)).build();
  }

  @POST
  public Response createCategory(ProductCategory newProductCategory) throws SQLException {
    return Response.ok(productCategoryService.createProductCategory(newProductCategory)).build();
  }

  @PUT
  @Path("/{id}")
  public Response updateProductCategory(@PathParam("id") String id, ProductCategory updatedProductCategory) throws SQLException {

    return Response.ok(productCategoryService.updateProductCategory(id, updatedProductCategory)).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteProductCategory(@PathParam("id") String id) throws SQLException {
    Logger.getLogger(ProductCategoryResource.class.getName()).log(Level.INFO, "delete with id ", id);

    productCategoryService.deleteProductCategoryById(id);

    return Response.noContent().build();
  }

}
