package com.nimsoc.dropwizard.api.services;

import com.nimsoc.dropwizard.lib.model.Product;
import java.util.List;

public interface ProductService {

  Product findProductById(String id);

  List<Product> findProducts(Integer limit, Integer offset);

  Long findProductCount();

  Product createProduct(Product product/*, User user*/);

  Product deleteProduct(String id);
}
