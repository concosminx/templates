package com.nimsoc.dropwizard.api.services.impl;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.nimsoc.dropwizard.api.rest.mappers.ProductMapper;
import com.nimsoc.dropwizard.api.models.ProductDAO;
import com.nimsoc.dropwizard.api.models.db.ProductEntity;
import com.nimsoc.dropwizard.api.services.ProductService;
import com.nimsoc.dropwizard.api.services.exceptions.EmptyPayloadException;
import com.nimsoc.dropwizard.api.services.exceptions.ResourceNotFoundException;
import com.nimsoc.dropwizard.lib.model.Product;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

  private ProductDAO productDAO;

  private final Meter createMeter;
  private final Meter deleteMeter;

  public ProductServiceImpl(ProductDAO productDAO, MetricRegistry metricRegistry) {
    this.productDAO = productDAO;
    this.createMeter = metricRegistry.meter(ProductServiceImpl.class.getName() + ".create-product");
    this.deleteMeter = metricRegistry.meter(ProductServiceImpl.class.getName() + ".cancel-product");
  }

  @Override
  public Product findProductById(String id) {
    ProductEntity productEntity = productDAO.findById(id);
    if (productEntity == null) {
      throw new ResourceNotFoundException(Product.class.getSimpleName(), id);
    }
    return ProductMapper.toProduct(productEntity);
  }

  @Override
  public List<Product> findProducts(Integer limit, Integer offset) {
    List<ProductEntity> productEntities = productDAO.findAll(limit, offset);
    return productEntities.stream().map(ProductMapper::toProduct).collect(Collectors.toList());
  }

  @Override
  public Long findProductCount() {
    return productDAO.findAllCount();
  }

  @Override
  public Product createProduct(Product product/*, User user*/) {
    if (product == null) {
      throw new EmptyPayloadException(Product.class.getSimpleName());
    }

    //TODO
    //if (product.getCustomerId() != null) {
    //  customersClient.findCustomerById(product.getCustomerId(), user);
    //}
    Date date = Date.from(Instant.now());
    ProductEntity productEntity = ProductMapper.toProductEntity(product);
    productEntity.setId(null);
    productEntity.setUpdatedAt(date);
    productEntity.setCreatedAt(date);

    productDAO.create(productEntity);

    createMeter.mark();

    return ProductMapper.toProduct(productEntity);
  }

  @Override
  public Product deleteProduct(String id) {

    ProductEntity productEntity = productDAO.findById(id);

    if (productEntity == null) {
      throw new ResourceNotFoundException(Product.class.getSimpleName(), id);
    }

    productDAO.delete(productEntity);

    deleteMeter.mark();

    return ProductMapper.toProduct(productEntity);
  }
}
