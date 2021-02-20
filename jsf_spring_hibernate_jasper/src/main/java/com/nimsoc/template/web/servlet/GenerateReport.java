package com.nimsoc.template.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class GenerateReport extends HttpServlet {

  private static final Log log = LogFactory.getLog(GenerateReport.class);

  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    DataSource dataSource = (DataSource) context.getBean("dataSource");

    Map<String, Object> param = new HashMap();
    param.put("SESSION_ID", request.getSession().getId());
    param.put("IMGPATH", getServletContext().getRealPath("reports/watermark.jpg"));
   
    try (Connection conn = dataSource.getConnection();) {
      JasperPrint jasperPrint = JasperFillManager.fillReport(getServletContext().getRealPath("reports/cumparaturi.jasper"), param, conn);
      
      JRPdfExporter exporter = new JRPdfExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      response.setContentType("application/pdf ");
      response.setHeader("Content-disposition", "attachment; filename="+ "raport_sesiune_" + request.getSession().getId());
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      exporter.exportReport();
      
    } catch (SQLException ex) {
      log.error("SQL Error: ", ex);
    } catch (JRException ex) {
      log.error("Report generation Error: ", ex);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }
}
