package com.nimsoc.application.service.itest;

import com.nimsoc.application.service.MyService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyServiceIntegrationTest {

  MyService service = null;

  @Before
  public void setUp() {
    service = new MyService();
  }

  @Test
  public void testMyMethodIntegration() {
    assertEquals("Hello", service.myMethod());
  }

}
