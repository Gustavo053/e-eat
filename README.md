# e-eat

A simple restaurant delivery API.

## System parts

- api-auth: Responsible for authenticating and creating user credentials;
- api-gateway: Responsible for forwarding requests to services and validate the authentication token;
- eureka-server: Responsible for performing load balancing and registering services for communication;
- restaurant-service: Responsible for restaurant services;
- user-service: Responsible for user services.

## Diagrams

Below are some of the principal system diagrams.

### Overall system diagram:

<img src="./__readme/diagram.drawio.png" />

### User creation diagram:

<img src="./__readme/create-credentials.drawio.png" />

### Order diagram:

<img src="./__readme/create-order.drawio.png" />

## Developed by:

[Gustavo Pereira da Fonseca](https://www.linkedin.com/in/gustavo-pereira053/)
