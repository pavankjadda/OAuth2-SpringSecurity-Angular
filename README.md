# Spring Security implementation with Spring Session, Spring Data and Spring Boot

Spring Security implementation with Spring Session, Spring Data and Spring Boot. Following usecases covered

#### Requirments
1. SpringBoot (IntellijJ or Eclipse will be downloaded required dependencies after import)
2. MySql (create database named `springsessiondemo`)
3. Execute following sql commands to create oauth tables (mandatory). These tables hold OAuth2 tokens
```
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);
 
drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);
 
 SET SQL_MODE='ALLOW_INVALID_DATES';
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);
```
4. Run the project and Spring Data JPA creates all the tables necessary for Users and Roles
5. Insert following data into Database
```
/* Insert Data into User Table  */
INSERT INTO springsessiondemo.user VALUES(1,1,1,1,1,'$2a$12$pqcdXB.Xboa7pGNba51YHuWQVhlZM8TVguRUCL2ss8GOwhEqiqwOu','admin');
INSERT INTO springsessiondemo.user VALUES(2,1,1,1,1,'$2a$12$kRstkVGM88bvzGqxcWDX5eVDvT0iy40YPAKMRiKX9D2jIsXmKXOAK','user');


/* Insert Data into Role Table  */
insert into springsessiondemo.role values(1,'ROLE_USER');
insert into springsessiondemo.role values(2,'ROLE_ADMIN');
insert into springsessiondemo.role values(3,'ROLE_APIUSER');
insert into springsessiondemo.role values(4,'ROLE_DBA');
insert into springsessiondemo.role values(5,'ROLE_SELLER');
insert into springsessiondemo.role values(6,'ROLE_BUYER');


/* Insert Data into Privilege Table  */
insert into springsessiondemo.privilege values(1,'READ_PRIVILEGE');
insert into springsessiondemo.privilege values(2,'WRITE_PRIVILEGE');
insert into springsessiondemo.privilege values(3,'DELETE_PRIVILEGE');

/* Insert Data into UserRole Table  */
INSERT INTO `springsessiondemo`.`user_role`(`id`,`user_id`,`role_id`) VALUES (1,2,1);
INSERT INTO `springsessiondemo`.`user_role`(`id`,`user_id`,`role_id`) VALUES (2,1,2);

/* Insert Data into RolePrivilege Table  */
insert into springsessiondemo.role_privilege values(2,1);
insert into springsessiondemo.role_privilege values(2,2);
insert into springsessiondemo.role_privilege values(2,3);
insert into springsessiondemo.role_privilege values(1,1);

```
6 


