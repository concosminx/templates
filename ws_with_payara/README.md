Microservice with Payara example app
====================================

[based on Java EE 8 Microservices course]

- 2 subprojects (micro-lib and micro-api)
- start the server with cmd. `gradle microBundle microStart` (port: 8081)
- stop the server with cmd. `gradle microStop`
- test endpoints on `http://localhost:8081` with a bogus `Authorization` header

```
GET	/ws_with_payara/categories
POST /ws_with_payara/categories
DELETE /ws_with_payara/categories/{id}
GET	/ws_with_payara/categories/{id}
PUT	/ws_with_payara/categories/{id}
```

Run with Docker
---------------

- build the image
`docker build . -t youruser/youtimgname:yourtag`

- run a container (uses port `8080`)
`docker run -p 8080:8080 youruser/youtimgname:yourtag`


