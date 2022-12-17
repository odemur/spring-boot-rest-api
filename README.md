# REST API built on Java and Spring Boot
A REST API built on Java, Spring Boot and H2, PostgreSQL or MySQL Server database.

## Live demo on Heroku
**Endpoint for requests:** <br />
https://odemur-spring-boot-rest-api.herokuapp.com/customer

**URL Swagger UI:** <br />
https://odemur-spring-boot-rest-api.herokuapp.com/swagger-ui/index.html

## Screenshot

![REST API Spring Boot and Postman](https://odemur.com.br/wp-content/uploads/2022/12/spring-boot-rest-api-postman.png)
![REST API Spring Boot and Swagger UI](https://odemur.com.br/wp-content/uploads/2022/12/spring-boot-rest-api-swagger-ui.png)

## Technologies
  * Language: **Java**
  * Framework: **Spring Boot**
  * Build Automation Tool: **Apache Maven**
  * Database [default]: **H2**
  * Supported Database: **PostgreSQL and MySQL Server** 
  * Service API: **REST**

## Getting Started
Clone this project and running on you local machine. <br />
Only for development and testing purposes.

## Prerequisites
   * Java SDK 8
   * Java IDE
   * Apache Maven (only for Command Line)


## Running Application
Please follow carefully step by step instructions below:

### Using Docker for database

   * For PostgreSQL:

   ```bash
   docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=123 -d postgres
   ```
   ```bash
   docker exec -it postgres psql -U postgres 
   ```

   * For MySQL:
   ```bash
   docker run --name=mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql/mysql-server:5.7
   ```
   ```bash
   docker exec -it mysql mysql -uroot -p 
   ```

   Please create a database with the name: **customer** 
   ```
   CREATE DATABASE customer;
   ````
### Open Terminal and clone this project
 ```
 git clone https://github.com/odemur/spring-boot-rest-api.git
 ```

## Notes to use PostgreSQL

1. Add PostgreSQL dependencies on file **pom.xml**

```
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
```

2. Edit the file **"application.properties"** on the directory "src/main/resorces". <br />

**Parameters for PostgreSQL**
```
#spring.datasource.url = jdbc:postgresql://localhost:5432/customer
#spring.datasource.username	= postgres
#spring.datasource.password	= 123
#spring.jpa.hibernate.ddl-auto = update
#spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
```

## Notes to use MySQL

1. Add MySQL dependencies on file **pom.xml**

```
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
```
2. Edit the file **"application.properties"** on the directory "src/main/resorces". <br />

**Parameters for MySQL**
```
spring.datasource.url = jdbc:mysql://localhost:3306/customer
spring.datasource.username = root
spring.datasource.password = 123
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

#### Running this application with IDE:
   - Open this project folder with any Java IDE (IntelliJ IDEA, Eclipse, etc..)
   - Run the application using the IDE > Run Option
   
#### Running this application with Command Line:
   - Open Terminal
   - Change directory to the base project (spring-boot-rest-api)
   - Run the command
 ```
   mvn spring-boot-rest-api:run
 ```
 
 ## API Documentation 
 **URL**<br />
 http://localhost:8080
 
 To test API Endpoint is necessary an HTTP Client Tool. <br />
 I recommed the app **Postman** [https://www.postman.com/downloads]
 
### Create Customer

Create new customer from JSON structure.

* **Endpoint**

  /customer

* **Method**

  `POST`

* **Body Data (application/json)**

  ```json
     {
         "firstName": "John",
         "lastName": "Doe",
         "email": "john@mail.com"
      }
   ```
* **Response**

  * **Code:** 201<br />
    **Content:** 
    
    ```json
     {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john@mail.com"
      }
      ```

 ### List All Customers
 Returns JSON list of all customers data.
 
 * **Endpoint**
 
    /customer
    
 * **Method**
    
    `GET`
    
 * **Response**

    * **Code**: 200<br />
    
      **Content:** 
      
      ```json
      [{
            "id": 1,
            "firstName": "John",
            "lastName": "Doe",
            "email": "john@mail.com"
       }, 
       {
            "..."
      }]
       ```
  
### List Customer by Id
Returns JSON of a specific customer by ID.
 
* **Endpoint**
 
   /customer/:id
   
*  **URL Params**

   **Required**
 
   `id=[integer]`
    
* **Method**
    
   `GET`
    
* **Response**

   * **Code**: 200<br />
    
     **Content:** 
      
     ```json
     {
           "id": 1,
           "firstName": "John",
           "lastName": "Doe",
           "email": "john@mail.com"
      }
      ```

### Update Customer

  Updates customer with the values sent as JSON.<br />
  Returns an updated JSON structure.

* **Endpoint**

  /customer/:id

* **Method**

  `PUT`
  
* **URL Params**

  **Required:**
 
  `id=[integer]`

* **Body Data (application/json)**

  ```json
     {
        "firstName": "Mary",
        "lastName": "Jane",
        "email": "mary@mail.com"
     }
   ```
* **Success Response**

  * **Code:** 200<br />
  
    **Content:** 
    
    ```json
     {
        "id": 1,
        "firstName": "Mary",
        "lastName": "Jane",
        "email": "mary@mail.com"
     }
    ```
    
### Delete Customer
Delete customer by ID.
 
* **Endpoint**
 
   /customer/:id
    
* **Method:**
    
   `DELETE`
   
* **URL Params**

  **Required:**
 
  `id=[integer]`
    
* **Success Response:**

   * **Code**: 200<br />

