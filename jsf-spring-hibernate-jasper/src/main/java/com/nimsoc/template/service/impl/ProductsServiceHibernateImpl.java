package com.nimsoc.template.service.impl;

import com.nimsoc.template.objects.ShoppingCart;
import com.nimsoc.template.objects.ShoppingCartPk;
import com.nimsoc.template.objects.Products;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.nimsoc.template.service.ProductsService;

@Transactional
@Service("products")
public class ProductsServiceHibernateImpl implements ProductsService {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Products> getProducts() {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Products");
    List<Products> prod = new ArrayList();
    try {
      prod = query.list();
    } catch (HibernateException ex) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    return prod;
  }

  @Override
  public void addProduct(Products product, String sessionId) {
    ShoppingCart cump = new ShoppingCart(new ShoppingCartPk(sessionId, product.getId()), 1);
    Session session = sessionFactory.getCurrentSession();
    try {
      session.save(cump);
      session.flush();
    } catch (HibernateException ex) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
  }

  @Override
  public boolean isAdded(int productId, String sessionId) {
    boolean isAdded = false;
    try {
      Session currentSession = sessionFactory.getCurrentSession();
      isAdded = currentSession.get(ShoppingCart.class, new ShoppingCartPk(sessionId, productId)) != null;
    } catch (HibernateException ex) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    return isAdded;
  }
}
