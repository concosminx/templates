Microservice with Dropwizard example app
========================================

[based on Java EE 8 Microservices course]

- start the server with cmd. `gradle run` (ports: `9000` for application and `9002` for admin connectors)
- test endpoints on `http://localhost:9000` (default without Authentication)



```
GET /hello-world 
GET /products
POST /products
DELETE /products/{id} 
GET /products/{id}

POST /tasks/log-level (io.dropwizard.servlets.tasks.LogConfigurationTask)
POST /tasks/gc (io.dropwizard.servlets.tasks.GarbageCollectionTask)
```


Test app with authorization token from Keycloack 
==========================================

*Prerequisites*
- download and install Keycloack server
- add a new REALM (e.g. `DROPWIZARD`)
- for the new REALM, configure a new client (e.g. `dropwizard-app`) with protocol `openid-connect`
- for the new REALM, add a new role (e.g. `admin`)
- for the new REALM, add a new user (e.g. `testuser` with password `testpassword`, not temporary)
- map the `admin` role to the new user (Users / select `testuser` / Edit / Role Mappings)
- test authentication with your new user at: `http://localhost:[PORT]/auth/realms/DROPWIZARD/account/`
- check REALM configuration at: `http://localhost:[PORT]/auth/realms/DROPWIZARD/.well-known/openid-configuration`
- copy `token_endpoint` (e.g. `http://localhost:[PORT]/auth/realms/DROPWIZARD/protocol/openid-connect/token`) and make a **POST** request from Postman using body and `x-www-form-urlencoded` with keys and values: 
    - client_id = `dropwizard-app`
    - grant_type = `password`
    - username = `testuser`
    - password = `testpassword`
- we will use the `access_token` from server response to call out app


*Modify the app*
- activate the feature in `MainApplication.java` (`...register(new AuthDynamicFeature...`)
- annotate methods in `ProductResource` with  `@RolesAllowed("admin")`
- get the public key from Keycloack (under Realm Settings / Keys / Public Key ~ RS256) and put the value in `config.yml` under `authPublicKey` key

*Call the app with the acces token from Keycloack*
- choose a secured app endpoint (e.g. `http://localhost:9000/products`)
- create a `POST` request using a body (application/json) with the new product 

```json
{
    "code" : "R2",
    "description" : "Description R2",
    "value" : 1,
    "productCategoryId" : "2312"
}
```
and an `Authorization` header with the value `Bearer ` + the access token obtained in prerequisites section


 *Notes*
 - generate a new access token if you have problems authenticating (e.g. `Credentials are required to access this resource.` in Postman)
 - check the role mapping in case of `javax.ws.rs.ForbiddenException: User not authorized` exception in server log, and generate a new access token for the new mapping


