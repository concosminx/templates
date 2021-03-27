package com.nimsoc.dropwizard.api.rest.resources;

import com.codahale.metrics.annotation.Timed;
import com.nimsoc.dropwizard.api.services.ProductService;
import com.nimsoc.dropwizard.lib.model.Product;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

  private final ProductService productService;

  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @GET
  @UnitOfWork
  @Timed
  public Response getProduct(@QueryParam("limit") Integer limit,
          @QueryParam("offset") Integer offset) {

    List<Product> products = productService.findProducts(limit, offset);
    Long productsCount = productService.findProductCount();

    return Response.ok(products).header("X-Total-Count", productsCount).build();
  }

  @GET
  @Path("/{id}")
  @UnitOfWork
  @Timed
  public Response getProduct(@PathParam("id") String id) {

    Product product = productService.findProductById(id);

    return Response.ok(product).build();
  }

  @POST
  //@RolesAllowed("admin")
  @UnitOfWork
  @Timed
  public Response createProduct(Product newProduct/*, @Auth User user*/) {

    Product product = productService.createProduct(newProduct/*, user*/);

    return Response.ok(product).build();
  }

  @DELETE
  @Path("/{id}")
  @UnitOfWork
  @Timed
  public Response deleteProduct(@PathParam("id") String id) {

    Product product = productService.deleteProduct(id);

    return Response.ok(product).build();
  }
}
