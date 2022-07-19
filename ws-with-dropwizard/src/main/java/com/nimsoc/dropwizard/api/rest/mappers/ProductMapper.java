package com.nimsoc.dropwizard.api.rest.mappers;

import com.nimsoc.dropwizard.api.models.db.ProductEntity;
import com.nimsoc.dropwizard.lib.model.Product;

public class ProductMapper {

  public static Product toProduct(ProductEntity entity) {

    if (entity == null) {
      return null;
    }

    Product pc = new Product();
    pc.setId(entity.getId());
    pc.setCreatedAt(entity.getCreatedAt());
    pc.setUpdatedAt(entity.getUpdatedAt());
    pc.setCode(entity.getCode());
    pc.setDescription(entity.getDescription());
    pc.setValue(entity.getValue());
    pc.setProductCategoryId(entity.getProductCategoryId());
    return pc;
  }

  public static ProductEntity toProductEntity(Product product) {

    if (product == null) {
      return null;
    }

    ProductEntity entity = new ProductEntity();
    entity.setCode(product.getCode());
    entity.setDescription(product.getDescription());
    entity.setProductCategoryId(product.getProductCategoryId());
    entity.setValue(product.getValue());
    return entity;
  }
}
