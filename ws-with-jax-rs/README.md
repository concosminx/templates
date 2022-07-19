Microservice with SpringBoot and JAX-RS example app
===================================================

[based on Java EE 8 Microservices course (Packt)]

- start the server with cmd. `gradle bootRun` (port: 8080 check `application.properties` for configuration)
- test endpoints on `http://localhost:8080`

```
GET /persons
POST /persons
GET /persons/count
GET /persons/{id}
```
- test actuator endpoint `http://localhost:8080/actuator`
- create a fat jar with cmd. `gradle bootJar` and run it with `java -jar ws_with_springboot.jar`