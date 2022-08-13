# Tenisu application

## Build the API and launch the tests 

To build the API and launch the unit tests, open a terminal at the root of the project and type : 

```mvn clean install```

## Launch the API

To launch the API, open a terminal at the root of the project and type : 

```mvn spring-boot:run```

You can now access the API through ```http://localhost:8080/api/```

You also have a Swagger UI here : ```http://localhost:8080/api/swagger-ui/index.html```

## Cloud 

The application has been deployed to AWS and is available here : ```http://tenisu-sandbox.eu-central-1.elasticbeanstalk.com/api```

Swagger : ```http://tenisu-sandbox.eu-central-1.elasticbeanstalk.com/api/swagger-ui/index.html```