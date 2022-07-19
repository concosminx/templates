package com.nimsoc.dropwizard.api.rest.test;

import com.codahale.metrics.MetricRegistry;
import com.nimsoc.dropwizard.api.models.ProductDAO;
import com.nimsoc.dropwizard.api.models.db.ProductEntity;
import io.dropwizard.testing.junit.DAOTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.mock;

public class ProductDAOTest {

  @Rule
  public DAOTestRule database = DAOTestRule.newBuilder()
          .addEntityClass(ProductEntity.class)
          .build();

  private ProductDAO productDAO;

  @Before
  public void setUp() {
    productDAO = new ProductDAO(database.getSessionFactory(), mock(MetricRegistry.class));
  }

  @Test
  public void testCreateProduct() {

    Date date = Date.from(Instant.now());

    ProductEntity productEntity = new ProductEntity();
    productEntity.setUpdatedAt(date);
    productEntity.setCreatedAt(date);
    productEntity.setCode("A1");
    productEntity.setDescription("Description for A1");
    productEntity.setValue(1);
    productEntity.setProductCategoryId("21313");

    ProductEntity newOrderEntity = database.inTransaction(() -> productDAO.create(productEntity));

    assertThat(newOrderEntity.getId(), notNullValue());
  }
}
