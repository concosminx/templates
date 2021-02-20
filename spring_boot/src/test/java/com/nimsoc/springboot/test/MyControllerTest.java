package com.nimsoc.springboot.test;

import com.nimsoc.springboot.MyController;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MyControllerTest {

  @Test
  public void testAdd() {
    assertEquals(42, Integer.sum(19, 23));
  }

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new MyController())
            .build();
  }

  public class RenderHelloWorldView {

    @Test
    public void shouldReturnResponseStatusOk() throws Exception {
      mockMvc.perform(get("/"))
              .andExpect(status().isOk());
    }

    @Test
    public void shouldRenderTheHelloWorldView() throws Exception {
      mockMvc.perform(get("/"))
              .andExpect(view().name("index"));
    }
  }
}
