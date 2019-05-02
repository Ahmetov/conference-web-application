

INSERT INTO ROOM(location) VALUES ('большой зал');
INSERT INTO ROOM(location) VALUES ('малый зал');


INSERT INTO PRESENTATIONS(title, description, pres_room_id, pres_time) VALUES ('Spring boot','доклад про springboot',1, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss'));
INSERT INTO PRESENTATIONS(title, description, pres_room_id, pres_time) VALUES ('Gradle','доклад про gradle',1, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss'));
INSERT INTO PRESENTATIONS(title, description, pres_room_id, pres_time) VALUES ('Hibernate','доклад про hibernate',2, parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss'));

INSERT INTO USR(user_id, username,password,active) VALUES(1,'admin','admin',0);
INSERT INTO USER_ROLE(USER_ID,ROLES) VALUES (1,'USER');
INSERT INTO USER_ROLE(USER_ID,ROLES) VALUES (1,'ADMIN');


