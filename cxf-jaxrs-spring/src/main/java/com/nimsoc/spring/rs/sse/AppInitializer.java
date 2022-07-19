package com.nimsoc.spring.rs.sse;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext container) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(StatsConfig.class);
    container.addListener(new ContextLoaderListener(context));

    ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new CXFServlet());
    dispatcher.addMapping("/rest/*");
    dispatcher.setAsyncSupported(true);
  }
}
