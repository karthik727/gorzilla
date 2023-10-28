INSERT INTO USER (USERNAME, PASSWORD, ISACTIVE,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ('admin', 'admin', 'Y','admin',sysdate(),'admin',sysdate(),'admin');
commit;
INSERT INTO USER_INFO (USERID, EMAILID, FIRSTNAME,MIDDLENAME,LASTNAME,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ((select userid from user where username='admin'), 'karthik727@gmail.com', 'karthik',null,'kumbhari','admin',sysdate(),'admin',sysdate(),'admin');
commit;
INSERT INTO USER_ADDRESS (USERID, ADDRESS, CITY,PINCODE,COUNTRY,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ((select userid from user where username='admin'), 'ECO WORLD BT', 'BENGALURU',560066,'INDIA','admin',sysdate(),'admin',sysdate(),'admin');
commit;
INSERT INTO ROLE (ROLENAME, ROLEDESCRIPTION,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ( 'admin', 'Full access to the system','admin',sysdate(),'admin',sysdate(),'admin'); 
 INSERT INTO ROLE (ROLENAME, ROLEDESCRIPTION,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ( 'user', 'User access to the system','admin',sysdate(),'admin',sysdate(),'admin'); 
 commit;
INSERT INTO USER_ROLE (USERID, ROLEID,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ( (select userid from user where username='admin'), (select ROLEID from ROLE where ROLENAME='admin'),'admin',sysdate(),'admin',sysdate(),'admin');
INSERT INTO USER_ROLE (USERID, ROLEID,CREATEDBY,CREATEDDATE,LASTUPDATEDBY,LASTUPDATEDDATE,LASTUPDATEDLOGIN)
 VALUES ( (select userid from user where username='admin'), (select ROLEID from ROLE where ROLENAME='user'),'admin',sysdate(),'admin',sysdate(),'admin');
 commit;
