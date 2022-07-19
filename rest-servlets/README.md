Modified version of **Hands-On RESTful Web Services with Java 11** [seen here](https://github.com/PacktPublishing/Hands-On-RESTful-Web-Services-with-Java-11) using `gradle` and `gretty` plugin

Prerequisites

- install PostgreSQL DB (local installation or Docker container)
- run sql scripts from `rest_servlets\src\test\resources` 

Start / stop app `gradle appStart` / `gradle appStop`

Test routes with Postman and use header `Authorization` with value `123`

```GET /todos
GET /todos/:id
POST /todos
POST /todos/:id
PUT /todos/:id
DELETE /todos/:id
GET /todos/:todoId/tasks
GET /todos/:todoId/tasks/:taskId
POST /todos/:todoId/tasks
POST /todos/:todoId/tasks/:taskId
PUT /todos/:todoId/tasks/:taskId
DELETE /todos/:todoId/tasks/:taskId
```

Run tests

`gradle test`