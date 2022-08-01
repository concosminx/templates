package com.nimsoc.micro.invoice;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InvoiceRepo {

  private final List<Invoice> allInvoices = new ArrayList<>();

  @PostConstruct
  private void init() {

    final Random r = new Random();

    IntStream.range(1, 10).forEach(
            i
            -> allInvoices.add(
                    new Invoice(
                            String.valueOf(i),
                            r.nextBoolean() ? InvoiceStatus.IN_PROGRESS : InvoiceStatus.COMPLETED,
                            new BigDecimal(r.nextDouble() * Math.pow(10d, 3), MathContext.DECIMAL32),
                            r.nextInt(15),
                            UUID.randomUUID().toString())
            )
    );
  }

  public List<Invoice> findAllInvoices() {
    return allInvoices.stream().map(Invoice::new).collect(Collectors.toList());
  }
}
