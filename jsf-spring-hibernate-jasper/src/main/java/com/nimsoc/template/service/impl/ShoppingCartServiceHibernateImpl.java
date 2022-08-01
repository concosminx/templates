package com.nimsoc.template.service.impl;

import com.nimsoc.template.web.servlet.GenerateReport;
import com.nimsoc.template.objects.ShoppingCart;
import com.nimsoc.template.objects.Products;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.nimsoc.template.service.ShoppingCartService;

@Transactional
public class ShoppingCartServiceHibernateImpl implements ShoppingCartService {

  DataSource dataSource;

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }
  GenerateReport psf;
  private static final Log log = LogFactory.getLog(ShoppingCartServiceHibernateImpl.class);

  private SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<ShoppingCart> getShoppingList(String session_ID) {
    StringBuilder HQL = new StringBuilder();
    HQL.append("Select C, P ")
            .append("FROM Products P, ShoppingCart C ")
            .append("WHERE C.pk.sessionId = :sid and C.pk.productId = P.id ");

    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(HQL.toString());
    query.setParameter("sid", session_ID);

    List<ShoppingCart> shoppingList = new ArrayList();
    try {
      List<Object[]> hql = query.list();
      for (Object[] cartItemAndProduct : hql) {
        ShoppingCart crtItem = (ShoppingCart) cartItemAndProduct[0];
        Products crtProduct = (Products) cartItemAndProduct[1];
        crtItem.setCode(crtProduct.getCode());
        crtItem.setDescription(crtProduct.getDescription());
        crtItem.setPrice(crtProduct.getPrice());
        shoppingList.add(crtItem);
      }
    } catch (HibernateException ex) {
      log.error(ex);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    return shoppingList;
  }

  @Override
  public void deleteShoppingCartItem(ShoppingCart shoppingCartItem) {
    if (log.isErrorEnabled()) {
      log.error("Delete with: " + shoppingCartItem);
    }
    Session session = sessionFactory.getCurrentSession();
    try {
      session.delete(shoppingCartItem);
    } catch (HibernateException ex) {
      log.error("delete error ", ex);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
  }

  @Override
  public void incrementQuantity(ShoppingCart shoppingCartItem) {
    Session session = sessionFactory.getCurrentSession();
    shoppingCartItem.setQuantity(shoppingCartItem.getQuantity()+ 1);
    try {
      session.update(shoppingCartItem);
    } catch (HibernateException ex) {
      log.error("increment error ", ex);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
  }

  @Override
  public void decrementQuantity(ShoppingCart shoppingCartItem) {
    if (shoppingCartItem.getQuantity()> 1) {
      Session session = sessionFactory.getCurrentSession();
      shoppingCartItem.setQuantity(shoppingCartItem.getQuantity()- 1);
      try {
        session.update(shoppingCartItem);
      } catch (HibernateException ex) {
        log.error("Eroare la decrementare ", ex);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      }
    }
  }

  @Override
  public void emptyShoppingCart(String session_ID) {
    String hql = "DELETE FROM ShoppingCart "
            + "WHERE pk.sessionId = :si";
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(hql);
    query.setParameter("si", session_ID);
    try {
      query.executeUpdate();
    } catch (HibernateException ex) {
      log.error("empty cart error ", ex);
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
  }
}
