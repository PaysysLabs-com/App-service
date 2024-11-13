# App Service System (Backend)


Appservice is the backend solution powering the **Merchant-App** and **Customer-App**, designed to handle all essential backend operations for both applications. It manages data processing, secure transaction handling, and API communication to support smooth interactions and real-time updates between merchants and customers. With robust integrations. Appservice ensures high performance, data integrity, and secure exchanges for seamless user experiences across both applications.


## Steps To install 
- Handles API requests based on host specifications.
- Supports authentication and response handling via Queue Processor.
- [Follow this link to install Spring Boot and run AppService](https://docs.spring.io/spring-boot/how-to/deployment/installing.html)  
- [Unirest Documentation](http://kong.github.io/unirest-java/)
- [Learn about Spring Boot for REST APIs](https://spring.io/guides/gs/rest-service/)
  
## Tech Stack & Tools

- **Language & Framework**: Java with [Spring Boot](https://spring.io/projects/spring-boot) (REST APIs, JPA, Hibernate)
   - [Install Maven](https://maven.apache.org/install.html) to build and run the project.
   - [JPA and Hibernate Guide](https://www.baeldung.com/hibernate-5-jpa)


- **Database**: [MySQL](https://www.mysql.com/)
   - Update database configurations in `application.yaml`.

- **Logging**: [Log4j](https://logging.apache.org/log4j/2.x/) for structured logs.
   - [Log4j Setup Guide](https://www.baeldung.com/log4j-2-configuration)

- **API Communication**: [Unirest](http://kong.github.io/unirest-java/)
   - Handles API requests and authentication in Rest Handler.

- **Caching**: [Redis](https://redis.io/) for performance optimization.
   - [Redis with Spring Boot](https://www.baeldung.com/spring-boot-redis-cache)

- **API Documentation**: [Swagger](https://swagger.io/)
   - [Swagger Setup in Spring Boot](https://www.baeldung.com/spring-rest-openapi-documentation)

- **Security**: IP Validation and token-based authentication for transactions.

## Prerequisites

1. **Java**: Install Java 1.8.
2. **Maven**: Install [Maven](https://maven.apache.org/install.html) to build the project.
3. **MySQL**: Install [MySQL](https://dev.mysql.com/downloads/installer/) and create a database for the system.
4. **RabbitMQ**: Install [RabbitMQ](https://www.rabbitmq.com/download.html).
5. **Redis**: Install [Redis](https://redis.io/download) for caching.
6. **Spring Boot**:[Learn about Spring Boot for REST APIs](https://spring.io/guides/gs/rest-service/)

