package com.nimsoc.dropwizard.api.rest.test;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.nimsoc.dropwizard.api.models.ProductDAO;
import com.nimsoc.dropwizard.api.models.db.ProductEntity;
import com.nimsoc.dropwizard.api.services.impl.ProductServiceImpl;
import com.nimsoc.dropwizard.lib.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Date;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  private ProductDAO productDAOMock;

  @Mock
  private MetricRegistry metricRegistryMock;

  @Before
  public void setup() {

    Date date = Date.from(Instant.now());

    ProductEntity productEntity = new ProductEntity();
    productEntity.setId("12345");
    productEntity.setUpdatedAt(date);
    productEntity.setCreatedAt(date);
    productEntity.setCode("B1");
    productEntity.setDescription("Description for B1");
    productEntity.setValue(1);
    productEntity.setProductCategoryId("9892");

    when(productDAOMock.findById("12345")).thenReturn(productEntity);
  }

  @After
  public void tearDown() {
    reset(productDAOMock);
  }

  @Test
  public void testFindProductById() {
    Product product = productService.findProductById("12345");

    verify(productDAOMock).findById("12345");

    assertThat(product, is(not(nullValue())));
  }
}
