package com.nimsoc.dropwizard.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MainConfiguration extends Configuration {

  @Valid
  @NotNull
  @JsonProperty("database")
  private final DataSourceFactory database = new DataSourceFactory();

  @NotNull
  private String authPublicKey;

  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  public DataSourceFactory getDatabase() {
    return database;
  }

  public String getAuthPublicKey() {
    return authPublicKey;
  }
}
