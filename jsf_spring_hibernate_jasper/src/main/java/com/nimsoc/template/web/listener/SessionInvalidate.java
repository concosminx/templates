package com.nimsoc.template.web.listener;

import com.nimsoc.template.web.ProductsBB;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebListener
public class SessionInvalidate implements HttpSessionListener {
  @Override
  public void sessionCreated(HttpSessionEvent hse) {
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent hse) {
    ApplicationContext aplicationContext = WebApplicationContextUtils.getWebApplicationContext(hse.getSession().getServletContext());
    ProductsBB bbRef = (ProductsBB) aplicationContext.getBean("productsBB");
    bbRef.emptyShoppingCart();
  }
}
