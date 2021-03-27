package com.nimsoc.payara.api.rest.services.impl;

import com.nimsoc.payara.api.rest.mappers.ProductCategoryMapper;
import com.nimsoc.payara.api.rest.models.db.ProductCategoryEntity;
import com.nimsoc.payara.api.rest.services.ProductCategoryService;
import com.nimsoc.payara.api.rest.services.exceptions.EmptyPayloadException;
import com.nimsoc.payara.api.rest.services.exceptions.IdMismatchException;
import com.nimsoc.payara.api.rest.services.exceptions.ResourceNotFoundException;
import com.nimsoc.payara.lib.model.ProductCategory;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ProductCategoryServiceImpl implements ProductCategoryService {

  @PersistenceContext
  EntityManager em;

  @Override
  public ProductCategory findProductCategoryById(String id) throws SQLException {
    ProductCategoryEntity pcEntity = em.find(ProductCategoryEntity.class, id);

    if (pcEntity == null) {
      throw new ResourceNotFoundException(ProductCategory.class.getSimpleName(), id);
    }

    return ProductCategoryMapper.toProductCategory(pcEntity);
  }

  @Override
  public List<ProductCategory> findProductCategories(Integer limit, Integer offset) throws SQLException {

    TypedQuery<ProductCategoryEntity> query = em
            .createNamedQuery("ProductCategoryEntity.findAll", ProductCategoryEntity.class);

    if (limit != null && limit > 0) {
      query = query.setMaxResults(limit);
    }

    if (offset != null && offset > 0) {
      query = query.setFirstResult(offset);
    }

    List<ProductCategoryEntity> customerEntities = query.getResultList();

    return customerEntities.stream().map(ProductCategoryMapper::toProductCategory).collect(Collectors.toList());

  }

  @Override
  public ProductCategory createProductCategory(ProductCategory productCategory) throws SQLException {
    if (productCategory == null) {
      throw new EmptyPayloadException(ProductCategory.class.getSimpleName());
    }

    ProductCategoryEntity productCategoryEntity = ProductCategoryMapper.toProductCategoryEntity(productCategory);
    productCategoryEntity.setId(null);

    em.persist(productCategoryEntity);

    return ProductCategoryMapper.toProductCategory(productCategoryEntity);

  }

  @Override
  public ProductCategory updateProductCategory(String id, ProductCategory productCategory) throws SQLException {
    if (productCategory == null) {
      throw new EmptyPayloadException(ProductCategory.class.getSimpleName());
    }

    if (!id.equals(productCategory.getId())) {
      throw new IdMismatchException(id, productCategory.getId());
    }

    ProductCategoryEntity productCategoryEntity = em.find(ProductCategoryEntity.class, id);

    if (productCategoryEntity == null) {
      throw new ResourceNotFoundException(ProductCategory.class.getSimpleName(), id);
    }

    ProductCategoryEntity updatedProductCategoryEntity = ProductCategoryMapper.toProductCategoryEntity(productCategory);

    updatedProductCategoryEntity.setId(id);
    updatedProductCategoryEntity.setCreatedAt(productCategoryEntity.getCreatedAt());

    updatedProductCategoryEntity = em.merge(updatedProductCategoryEntity);

    return ProductCategoryMapper.toProductCategory(updatedProductCategoryEntity);
  }

  @Override
  public void deleteProductCategoryById(String id) throws SQLException {
    ProductCategoryEntity productCategoryEntity = em.find(ProductCategoryEntity.class, id);

    if (productCategoryEntity == null) {

      throw new ResourceNotFoundException(ProductCategory.class.getSimpleName(), id);
    }
    em.remove(productCategoryEntity);
  }

}
