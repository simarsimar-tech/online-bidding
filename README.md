# online-bidding

The Bidding System

This bidding system enables the users to place the bid for running auctions.

Tech Stack :

1. Spring Boot is used for development of project. Spring Boot helps you to create Spring-powered, production-grade applications and services with absolute minimum fuss. It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need.
2. In Memory H2 Database is selected
which can be accessed at -- http://localhost:8080/h2-console


API

1. Fetch Running auctions
GET /v1/auction?status=RUNNING


2. Place Bid
POST /v1/auction/{itemCode}/bid Request Payload: bidAmount






To run this project :

1. Sample Data is embedded in data-h2.sql file in resources, this will setup data in h2 at time of initialization.

2. User should be logged in to call the API's.

POST Call - /v1/users/token?user_name={user_name}&password={password}

This will generate a token.

3. Above generated token will be used in Authorization header with Bearer part.

4. Without authorization, user will get unauthorized error.


