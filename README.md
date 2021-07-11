# SimpleBankingProject

## How to run

Please run it with maven:
```
mvn clean spring-boot:run
```

## How to test it

### Create a new account

```
curl -H "Content-Type: application/json" -X POST -d "{\"customerId\": 1, \"amount\": -25}" http://localhost:8180/currentaccount/accounts/v1/new
```

### Query a customer

There are 3 customers available with ids 1,2,3
|Username|ID|
| --- | --- |
|user1|1|
|user2|2|
|user3|3|

#### by ID

```
curl http://localhost:8180/currentaccount/accounts/v1/by-customer/1
```

#### by username

```
curl http://localhost:8180/currentaccount/accounts/v1/by-username/user1
```

## Swagger UI

### How to access
Swagger UI is available under the link:
http://localhost:8180/currentaccount/swagger-ui/

There you could run above queries using UI "try it" forms