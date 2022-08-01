package com.nimsoc.payara.api.rest.services;

import com.nimsoc.payara.lib.model.ProductCategory;
import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryService {

  ProductCategory findProductCategoryById(String id) throws SQLException;

  List<ProductCategory> findProductCategories(Integer limit, Integer offset) throws SQLException;

  ProductCategory createProductCategory(ProductCategory productCategory) throws SQLException;

  ProductCategory updateProductCategory(String id, ProductCategory productCategory) throws SQLException;

  void deleteProductCategoryById(String id) throws SQLException;
}
