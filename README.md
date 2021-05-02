 **Building Application**

`mvn clean install`

creates a jar file in ~/momentum-store/target/momentum-store-0.0.1-SNAPSHOT.jar


**Runnning Application**

`mvn spring-boot:run`


**Swagger UI**

http://localhost:8080/momentum-store/swagger-ui.html

**Swagger docs**

`http://localhost:8080/momentum-store/v2/api-docs`

**REST API**

Request:

`GET /store/products`

`curl -X GET "http://localhost:8080/momentum-store/store/products" -H "accept: */*"`

Response:

`[
   {
     "name": "Product 1",
     "code": 1,
     "points": 150
   },
   {
     "name": "Product 2",
     "code": 2,
     "points": 300
   },
   {
     "name": "Product 3",
     "code": 3,
     "points": 450
   },
   {
     "name": "Product 4",
     "code": 4,
     "points": 600
   },
   {
     "name": "Product 5",
     "code": 5,
     "points": 750
   }
 ]`
 
 Request:
 
 `GET store/1/purchase-product`
 
 `curl -X POST "http://localhost:8080/momentum-store/store/1/purchase-product" -H "accept: */*" -H "Content-Type: application/json" -d "[ { \"name\": \"Product1\", \"code\": 1, \"points\": 10 }, { \"name\": \"Phys Fintone\", \"code\": 2, \"points\": 20 }, { \"name\": \"New-Sing\", \"code\": 3, \"points\": 30 }]"`
 
 Response:
 
 `[
    {
      "name": "Product 1",
      "code": 1,
      "points": 150
    },
    {
      "name": "Product 2",
      "code": 2,
      "points": 300
    },
    {
      "name": "Product 3",
      "code": 3,
      "points": 450
    }
  ]`
  
  **Dependencies needed for intellij**
  
  Lombok
  
  `        <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
           `