# Getir Tech Chalange
## Architectural Design
![Design@2x](https://user-images.githubusercontent.com/33653098/117581948-5ba00300-b108-11eb-85b2-2847dfaf1ade.jpg)

## Basic explanations about capabilities
- Auth service can register a user
- User can list all books
- User can update the stock information of book
- User can create an order
- User can obtain a detail of order
- User can list all orders that owns them
- User can sync the stock information of orders

## Running instructions on local machine

First, we need to run mongo db on docker machine
```
docker run -d --name mongodb -p 27017:27017 mongo
```
Then for each service, go to folder destination and run
```
mvn clean package
```
Finally you can run jar files. Go project folder and run
```
java -jar auth-service/target/auth-service-0.0.1-SNAPSHOT.jar
```
```
java -jar book-service/target/book-service-0.0.1-SNAPSHOT.jar
```
```
java -jar order-service/target/order-service-0.0.1-SNAPSHOT.jar
```
```
java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
```
You can use the given postman collection (**Getir.postman_collection.json**) for testing the APIs

## Running instructions on docker
Go to destination of each service and run
```
mvn clean package
```
Then go to project folder and run
```
docker-compose up --build -d
```
You can use the given postman collection (**Getir.postman_collection.json**) for testing the APIs
