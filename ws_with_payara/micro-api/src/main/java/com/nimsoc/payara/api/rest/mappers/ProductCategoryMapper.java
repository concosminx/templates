package com.nimsoc.payara.api.rest.mappers;

import com.nimsoc.payara.api.rest.models.db.ProductCategoryEntity;
import com.nimsoc.payara.lib.model.ProductCategory;

public class ProductCategoryMapper {

  public static ProductCategory toProductCategory(ProductCategoryEntity entity) {

    if (entity == null) {
      return null;
    }

    ProductCategory pc = new ProductCategory();
    pc.setId(entity.getId());
    pc.setCreatedAt(entity.getCreatedAt());
    pc.setUpdatedAt(entity.getUpdatedAt());
    pc.setCode(entity.getCode());
    pc.setDescription(entity.getDescription());
    return pc;
  }

  public static ProductCategoryEntity toProductCategoryEntity(ProductCategory productCategory) {

    if (productCategory == null) {
      return null;
    }

    ProductCategoryEntity entity = new ProductCategoryEntity();
    entity.setCode(productCategory.getCode());
    entity.setDescription(productCategory.getDescription());

    return entity;
  }
}
