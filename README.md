# spring-boot-react-demo

## Prerequisites
* JDK 11 (You can use SDKMAN to install Jdk 11)
* NodeJS 14
* Docker and DockerCompose

## Running application using Docker/DockerCompose

1. `./run.sh start_db` - This will start Postgres 12.3 database in a docker container. 
  You can use any DB Client(ex: DBeaver) to connect to it using following credentials.
  * Jdbc URL: `jdbc:postgresql://localhost:5432/todolistdb`
  * Username: `postgres`
  * Password: `postgres`
    
2. `./run.sh start_api` - This will start both postgres database and SpringBoot API Server in docker.
    You can access **SwaggerUI** at http://localhost:8080/swagger-ui.html
   
3. `./run.sh start` - This will start all postgres database, SpringBoot API Server and React UI in docker.
    You can access the application at http://localhost:3000

## Local Development

**1. Back-end**
```shell
./run.sh start_db
cd server
./gradlew bootRun
```

**2. Front-End**
```shell
cd client
yarn install
yarn start
```

**3. E2E Testing using Cypress**

Steps to run cypress:
```shell
cd e2e-tests
npx cypress open
```
Link for official documentation https://docs.cypress.io/guides/getting-started/writing-your-first-test#Write-your-first-test

## Swagger UI:
* Swagger ui can be accessed in the following url: http://localhost:8080/swagger-ui.html
* To invoke apis, follow these steps:
    - Get jwt token from the endpoint - /user/login using the above credentials.
    - Click on authorize option on the top right in the UI and enter the value as:
      `Bearer <token>`
    - This token will be included for all requests from swagger ui.
    - Now all the requests can be performed as similar to executing in a postman software.
    