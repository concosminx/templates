package com.nimsoc.jr;

import com.nimsoc.jr.model.Product;
import com.nimsoc.jr.model.ProductType;
import com.nimsoc.jr.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class JrApplication {

  public static void main(String[] args) {
    SpringApplication.run(JrApplication.class, args);
  }


  @Bean
  public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
    return args -> {
      List<Product> products = List.of(
              new Product("Lenovo IdeaPad",
                      "Core™ i3-1115G4",
                      ProductType.COMPUTER,
                      new BigDecimal("1540"),
                      LocalDate.now()),
              new Product("ASUS A516KA",
                      "Celeron® N4500",
                      ProductType.COMPUTER,
                      new BigDecimal("1267"),
                      LocalDate.now()),
              new Product("ASUS VivoBook 15X",
                      "AMD Ryzen™ 5",
                      ProductType.COMPUTER,
                      new BigDecimal("2500"),
                      LocalDate.now().minusDays(1)),
              new Product("ASUS A516MA",
                      "Celeron® N4020",
                      ProductType.COMPUTER,
                      new BigDecimal("1258"),
                      LocalDate.now().minusDays(1)),
              new Product("Apple MacBook Air",
                      "13-inch",
                      ProductType.COMPUTER,
                      new BigDecimal("4700"),
                      LocalDate.now().minusDays(1)),
              new Product("Lenovo Yoga Slim 6",
                      "Intel® Core™ i7-1260P",
                      ProductType.COMPUTER,
                      new BigDecimal("4500"),
                      LocalDate.now().minusDays(1))
      );

      productRepository.saveAll(products);
    };
  }
}
