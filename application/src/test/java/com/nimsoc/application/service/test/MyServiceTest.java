package com.nimsoc.application.service.test;

import com.nimsoc.application.service.MyService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyServiceTest {

  MyService service = null;

  @Before
  public void setUp() {
    service = new MyService();
  }

  @Test
  public void testMyMethod() {
    assertEquals("Hello", service.myMethod());
  }

}
