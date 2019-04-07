-- noinspection SqlNoDataSourceInspectionForFile

/* Insert Data into Category Table */
INSERT INTO springsessiondemo.category VALUES(1002,'Grocery');
INSERT INTO springsessiondemo.category VALUES(1003,'Electronics');
INSERT INTO springsessiondemo.category VALUES(1001,'Books');


/* Insert Data into Product Table  */
INSERT INTO springsessiondemo.product VALUES(2001,'iPhoneX',1003);
INSERT INTO springsessiondemo.product VALUES(2002,'GalaxyS10',1003);
INSERT INTO springsessiondemo.product VALUES(2003,'Google Pixel',1003);

/* Insert Data into Order Detail Table  */
INSERT INTO springsessiondemo.order_detail VALUES(1001,1001);

/* Insert Data into Order Detail Products Table  */
INSERT INTO springsessiondemo.order_detail_productlist VALUES(1,'ord1001',2001);
INSERT INTO springsessiondemo.order_detail_productlist VALUES(2,'ord1001',2002);
INSERT INTO springsessiondemo.order_detail_productlist VALUES(3,'ord1001',2003);





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
