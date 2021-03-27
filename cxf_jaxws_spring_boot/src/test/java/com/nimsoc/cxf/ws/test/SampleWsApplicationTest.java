package com.nimsoc.cxf.ws.test;


import com.nimsoc.cxf.ws.SampleWsApplication;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleWsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SampleWsApplicationTest {

    //CHECKSTYLE:OFF
    @Rule
    public OutputCaptureRule output = new OutputCaptureRule(); // SUPPRESS CHECKSTYLE
    //CHECKSTYLE:ON

    private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        this.webServiceTemplate.setDefaultUri("http://localhost:" + this.port + "/services/Hello");
    }

    @Test
    public void testHelloRequest() {
        String request = "<q0:sayHello xmlns:q0=\"http://service.ws.sample/\"><myname>Batman</myname></q0:sayHello>";

        StreamSource source = new StreamSource(new StringReader(request));
        StreamResult result = new StreamResult(System.out);

        this.webServiceTemplate.sendSourceAndReceiveToResult(source, result);
        assertThat(this.output.toString(),
                   containsString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                                  + "<ns2:sayHelloResponse xmlns:ns2=\"http://service.ws.sample/\">"
                                  + "<return>Hello, Welcome to CXF Spring boot Batman!!!</return>"
                                  + "</ns2:sayHelloResponse>"));
    }

}