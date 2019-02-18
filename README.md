# Spring Security implementation with Spring Session, Spring Data and Spring Boot

Spring Security implementation with Spring Session, Spring Data and Spring Boot. Following usecases covered

#### Requirments
1. SpringBoot (IntellijJ or Eclipse will be downloaded required dependencies after import)
2. MySql (create database named `springsessiondemo`)
3. Execute sql queries from [this page](https://github.com/pavankjadda/SpringSessionDemo/wiki/Create-OAuth-Tables) to create oauth tables (mandatory). These tables hold OAuth2 tokens
4. Insert following records in oauth_client_details table and make sure to change `CLIENT_SECRET` value. **Compute this value with BCrypt (4 times hash) online and replace it with hashed value**
```
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-client-password1234*/',
 'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);
 
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-write-client-password1234*/',
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);
 ```
5.Run the project and Spring Data JPA creates all the tables necessary for Users and Roles
6.Insert Roles and Users data mentioned in this [page](https://github.com/pavankjadda/SpringSessionDemo/wiki/Users-and-Roles)


