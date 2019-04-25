-- noinspection SqlNoDataSourceInspectionForFile


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
