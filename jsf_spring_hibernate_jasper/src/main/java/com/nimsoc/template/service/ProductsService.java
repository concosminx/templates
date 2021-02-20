package com.nimsoc.template.service;

import com.nimsoc.template.objects.Products;
import java.util.List;

public interface ProductsService {

  List<Products> getProducts();

  void addProduct(Products product, String sessionId);

  boolean isAdded(int productId, String sessionId);
}
