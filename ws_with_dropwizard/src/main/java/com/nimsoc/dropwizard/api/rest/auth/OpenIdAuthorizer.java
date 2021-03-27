package com.nimsoc.dropwizard.api.rest.auth;

import io.dropwizard.auth.Authorizer;

public class OpenIdAuthorizer implements Authorizer<User> {

  @Override
  public boolean authorize(User user, String s) {
    return user.getAccess().getRoles().contains(s);
  }
}
