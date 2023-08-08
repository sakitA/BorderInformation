# Getting Started

### Prerequisites
- Java version 1.8 or higher.
- Spring Boot version 2.7.14.
- Make sure to have Lombok enabled and properly configured in your IDE.

## Backend Developer Test
Your task is to create a simple Spring Boot service, that is able to calculate any possible land route from one country to another. The objective is to take a list of country data in JSON format and calculate the route by utilizing individual countries border information.

### Specifications:
* Spring Boot, Maven
* Data link: https://raw.githubusercontent.com/mledoze/countries/master/countries.json
* The application exposes REST endpoint /routing/{origin}/{destination}​ that
returns a list of border crossings to get from origin to destination
* Single route is returned if the journey is possible
* Algorithm needs to be efficient
* If there is no land crossing, the endpoint returns HTTP 400
* Countries are identified by **cca3** field in country data
* HTTP request sample (land route from Czech Republic to Italy):
* GET /routing/CZE/ITA HTTP/1.0 : 
```json
{
  "route": ["CZE", "AUT", "ITA"]
}
```

## Testing

Execute the application by running the provided command from the application's main directory.

```shell
mvn clean compile spring-boot:run
```

Navigate to http://localhost:8080/routing/cze/aze in your web browser.

or if curl installed execute the following command.

```shell
curl http://localhost:8080/routing/cze/aze
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

