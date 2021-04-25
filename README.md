# EBF Task

## Description
Backend application that maintains data for the following entities: 

* Employee
* Company 

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

You can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## How to use

The application exposes endpoints that are described using the OpenAPI spec definition

Once the application is running you are able to check these endpoints on this link:

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

In memory H2 database was used with SQL statements feeding data when the application starts



