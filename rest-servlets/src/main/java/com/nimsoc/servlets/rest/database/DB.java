package com.nimsoc.servlets.rest.database;

import org.dalesbred.Database;

public class DB {

  public static Database db;

  static {
    db = Database.forUrlAndCredentials("jdbc:postgresql://localhost:5432/todoapi", "todouser", "todopass");
  }
}
