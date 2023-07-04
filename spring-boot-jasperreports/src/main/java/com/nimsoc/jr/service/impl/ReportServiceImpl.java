package com.nimsoc.jr.service.impl;

import com.nimsoc.jr.model.Product;
import com.nimsoc.jr.repositories.ProductRepository;
import com.nimsoc.jr.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final ProductRepository productRepository;

  private JasperPrint getJasperPrint(List<Product> phoneCollection, String resourceLocation) throws FileNotFoundException, JRException {
    File file = ResourceUtils.getFile(resourceLocation);
    JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(phoneCollection);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("createdBy", "Cosmin");
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    return jasperPrint;
  }

  private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
    String uploadDir = StringUtils.cleanPath("./generated-reports");
    Path uploadPath = Paths.get(uploadDir);
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    if (fileFormat.equalsIgnoreCase("pdf")) {
      JasperExportManager.exportReportToPdfFile(jasperPrint, uploadPath + fileName);
    }

    return uploadPath;
  }

  private String getPdfFileLink(String uploadPath) {
    return uploadPath + "/" + "products.pdf";
  }

  @Override
  public String generateReport(LocalDate localDate, String fileFormat) throws JRException, IOException {
    List<Product> phoneCollection = productRepository.findAllByCreatedAt(localDate);
    String resourceLocation = "classpath:Products.jrxml";
    JasperPrint jasperPrint = getJasperPrint(phoneCollection, resourceLocation);
    String fileName = "/" + "products.pdf";
    Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);
    return getPdfFileLink(uploadPath.toString());
  }

  @Override
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }
}
