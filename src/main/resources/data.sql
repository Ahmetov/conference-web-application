CREATE TABLE IF NOT EXISTS T_TEST (
  ID INTEGER,
  VALUE VARCHAR
);


INSERT INTO USERS(login, password) VALUES ('aaa','123');
INSERT INTO USERS(login, password) VALUES ('aaa','123');
INSERT INTO USERS(login, password) VALUES ('aaa','123');

INSERT INTO ROOM(location) VALUES ('большой зал');
INSERT INTO ROOM(location) VALUES ('малый зал');

INSERT INTO PRESENTATIONS(title, description, pres_room_id) VALUES ('Spring boot','доклад про springboot',1);
INSERT INTO PRESENTATIONS(title, description, pres_room_id) VALUES ('Gradle','доклад про gradle',1);
INSERT INTO PRESENTATIONS(title, description, pres_room_id) VALUES ('Hibernate','доклад про hibernate',2);




