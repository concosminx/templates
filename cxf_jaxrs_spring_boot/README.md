Spring Boot - CXF Rest Web Services 
===================================

Starting the server
-------------------

`gradle run`

Testing the server from java client
-----------------------------------

`gradle client`

Testing the server from the browser
-----------------------------------

`http://localhost:8080/services/helloservice/sayHello/YourName`

will display "Hello YourName, Welcome to CXF RS Spring Boot World!!!"



`http://localhost:8080/services/helloservice/openapi.json `

will return a Swagger JSON description of services.

`http://localhost:8080/services/helloservice/api-docs?url=/services/helloservice/openapi.json`

OpenAPI document using Swagger-UI

`http://localhost:8080/actuator/metrics`

metrics

`http://localhost:8080/actuator/metrics/cxf.server.requests`

Apache CXF specific metrics