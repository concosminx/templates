Spark (https://sparkjava.com/) example with Gradle
==================================================

Starting the server
-------------------

`gradle run`

Testing the server (Postman)
---------------------

list users (GET)

`http://localhost:4567/users`

create a new user (POST)

`http://localhost:4567/users`

```
{
    "id": "2", 
    "email": "johndoe@your-domain.com", 
    "firstName": "John",
    "lastName": "Doe"
}
```

check if user exists (OPTIONS)

`http://localhost:4567/users/1013`

list specific user (GET)

`http://localhost:4567/users/1013`

delete a user (DELETE)

`http://localhost:4567/users/1013`

edit a user (PUT)

`http://localhost:4567/users/1013`

```
{
    "id": "2", 
    "email": "johndoenewmail@your-domain.com", 
    "firstName": "John",
    "lastName": "Doe"
}
```


