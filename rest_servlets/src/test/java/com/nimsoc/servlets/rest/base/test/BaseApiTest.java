package com.nimsoc.servlets.rest.base.test;

import com.nimsoc.servlets.rest.util.Util;
import com.nimsoc.servlets.rest.database.DB;
import com.nimsoc.servlets.rest.router.Request;
import com.nimsoc.servlets.rest.router.Response;
import com.nimsoc.servlets.rest.router.RouterServlet;
import org.dalesbred.Database;
import org.dalesbred.annotation.SQL;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseApiTest {

  @BeforeClass
  public static void setupFirstTime() {
    DB.db = Database.forUrlAndCredentials("jdbc:hsqldb:mem:memory_db;sql.syntax_pgs=true;sql.enforce_size=false", "SA", "");
  }

  @Before
  public void setup() {
    DB.db.update("DROP SCHEMA PUBLIC CASCADE");
    String[] instructions = Util.readInputStream(BaseApiTest.class.getResourceAsStream("/2_setup_tables.sql")).split(";");
    for (@SQL String instruction : instructions) {
      DB.db.update(instruction);
    }
  }

  protected Response request(String method, String uri, String body) {
    Request req = createRequestWithAuth(method, uri).withBody(body);
    return request(req);
  }

  protected Response request(String method, String uri) {
    Request req = createRequestWithAuth(method, uri);
    return request(req);
  }

  private Request createRequestWithAuth(String method, String uri) {
    return new Request(method, uri).withHeader("Authorization", "123");
  }

  protected Response request(Request req) {
    Response resp = new Response();
    new RouterServlet().genericHandler(req, resp);
    return resp;
  }
}
