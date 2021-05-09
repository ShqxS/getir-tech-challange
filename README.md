# Getir Tech Chalange
## Architectural Design
![Design@2x](https://user-images.githubusercontent.com/33653098/117555998-fea33f00-b06c-11eb-887e-2973c0ef4e3f.jpg)

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
You can use the given postman collection (**Getir.postman_collection.json**) for testing the APIs
