### Basic war example with Spring Framework, JSF and Hibernate

### Setup & Run

- prepare a tomcat 8+ instance and a PostgreSQL instance
- create database tables with [scripts](/src/main/resources/META-INF/ddl/postgres-ddl.sql)
- edit the `tomcatDir` in `gradle.properties`
- edit the data source properties in [context.xml](/src/main/webapp/META-INF/context.xml)
- run `gradle deployOnTomcat` to compile and deploy the war
- startup tomcat and verify logs 
- access the app `http://localhost:8080/jsf-spring-hibernate-jasper` 
