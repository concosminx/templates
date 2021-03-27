package com.nimsoc.dropwizard.api.models;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.nimsoc.dropwizard.api.models.db.ProductEntity;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ProductDAO extends AbstractDAO<ProductEntity> {

  private Timer findAllTimer;

  public ProductDAO(SessionFactory sessionFactory, MetricRegistry metricRegistry) {
    super(sessionFactory);
    this.findAllTimer = metricRegistry.timer(ProductDAO.class.getName() + ".query-find-all");
  }

  @SuppressWarnings("unchecked")
  public List<ProductEntity> findAll(Integer limit, Integer offset) {
    Query<ProductEntity> query = (Query<ProductEntity>) namedQuery("ProductEntity.findAll");
    if (limit != null && limit > 0) {
      query = query.setMaxResults(limit);
    }

    if (offset != null && offset > 0) {
      query = query.setFirstResult(offset);
    }

    final Timer.Context context = findAllTimer.time();

    try {
      return list(query);
    } finally {
      context.stop();
    }
  }

  public Long findAllCount() {
    return (Long) namedQuery("ProductEntity.findAllCount").uniqueResult();
  }

  public ProductEntity findById(String id) {
    return get(id);
  }

  public ProductEntity create(ProductEntity order) {
    return persist(order);
  }

  public void delete(ProductEntity entity) {
    currentSession().delete(entity);
  }

}
