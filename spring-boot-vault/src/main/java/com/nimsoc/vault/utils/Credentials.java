package com.nimsoc.vault.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("myprops")
public class Credentials {
  private String username;
  private String password;
}
