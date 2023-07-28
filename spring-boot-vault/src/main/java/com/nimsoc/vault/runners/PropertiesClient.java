package com.nimsoc.vault.runners;

import com.nimsoc.vault.utils.Credentials;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(Credentials.class)
@Slf4j
@AllArgsConstructor
public class PropertiesClient implements CommandLineRunner {

  private final Credentials credentials;

  @Override
  public void run(String... args) throws Exception {
    log.info("Username: " + credentials.getUsername());
    log.info("Password: " + credentials.getPassword());
  }
}
