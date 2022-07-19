package com.nimsoc.servlets.rest.router;

import com.nimsoc.servlets.rest.auth.Token;
import com.nimsoc.servlets.rest.database.DB;
import com.nimsoc.servlets.rest.status.StatusHandlers;
import com.nimsoc.servlets.rest.task.TasksHandler;
import com.nimsoc.servlets.rest.todo.TodosHandlers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RouterServlet extends HttpServlet {

  private static final Map<RouteDefinition, RouteHandler> ROUTES = new HashMap<>();

  private static void addRoute(String route, RouteHandler handler) {
    ROUTES.put(new RouteDefinition(route), handler);
  }

  static {
    addRoute("GET /todos", TodosHandlers::listTodos);
    addRoute("GET /todos/:id", TodosHandlers::fetchTodo);
    addRoute("POST /todos", TodosHandlers::createTodoWithoutId);
    addRoute("POST /todos/:id", TodosHandlers::createTodoWithId);
    addRoute("PUT /todos/:id", TodosHandlers::updateTodo);
    addRoute("DELETE /todos/:id", TodosHandlers::deleteTodo);

    addRoute("POST /status", StatusHandlers::startExpensiveOperation);
    addRoute("GET /status/:id", StatusHandlers::fetchCurrentStatus);

    addRoute("GET /todos/:todoId/tasks", TasksHandler::listTasks);
    addRoute("GET /todos/:todoId/tasks/:taskId", TasksHandler::fetchTask);
    addRoute("POST /todos/:todoId/tasks", TasksHandler::createTaskWithoutTaskId);
    addRoute("POST /todos/:todoId/tasks/:taskId", TasksHandler::createTaskWithTaskId);
    addRoute("PUT /todos/:todoId/tasks/:taskId", TasksHandler::updateTask);
    addRoute("DELETE /todos/:todoId/tasks/:taskId", TasksHandler::deleteTask);

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    genericHandler(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    genericHandler(req, resp);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    genericHandler(req, resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    genericHandler(req, resp);
  }

  @Override
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    genericHandler(req, resp);
  }

  private void genericHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Request wrappedReq = new Request(req);
    Response wrappedResp = new Response(resp);
    genericHandler(wrappedReq, wrappedResp);
    wrappedResp.send();
  }

  public void genericHandler(Request req, Response resp) {
    resp.addHeader("Access-Control-Allow-Origin", "*");
    resp.addHeader("Access-Control-Allow-Headers", "Authorization");
    resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    resp.addHeader("Access-Control-Allow-Credentials", "false");

    if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
      resp.setStatus(200);
      resp.setBody("Allowing OPTIONS");
      return;
    }

    String token = req.getHeader("Authorization");

    String actualToken = DB.db.findOptional(Token.class, "SELECT * FROM Tokens")
            .map(Token::getValue)
            .orElseThrow();

    boolean isVerified = actualToken.equals(token);

    if (!isVerified) {
      resp.setBody("Sorry, authentication required.");
      resp.setStatus(401); // 401, 403
      return;
    }

    for (Map.Entry<RouteDefinition, RouteHandler> route : ROUTES.entrySet()) {
      if (route.getKey().matches(req)) {
        route.getValue().execute(req, resp);
        return;
      }
    }
    noMatchHandler(resp);
  }

  private void noMatchHandler(Response resp) {
    resp.setStatus(404);
    resp.setBody("Route not found");
  }
}
