-- START : Create database if required --
drop database "jobportal";
create database "jobportal";
commit;
-- END : Create database if required --

-- START : Create users_type and users table --
CREATE TABLE users_type (
                            user_type_id int NOT NULL,
                            user_type_name varchar(255) DEFAULT NULL,
                            PRIMARY KEY (user_type_id)
);

INSERT INTO users_type VALUES (1,'Recruiter'),(2,'Job Seeker');

CREATE TABLE users (
                       user_id int NOT NULL,
                       email varchar(255) DEFAULT NULL,
                       is_active boolean DEFAULT NULL,
                       password varchar(255) DEFAULT NULL,
                       registration_date timestamp(6) DEFAULT NULL,
                       user_type_id int DEFAULT NULL,
                       PRIMARY KEY (user_id),
                       CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
                       CONSTRAINT FK5snet2ikvi03wd4rabd40ckdl FOREIGN KEY (user_type_id) REFERENCES users_type (user_type_id)
);

CREATE INDEX FK5snet2ikvi03wd4rabd40ckdl ON users (user_type_id);
-- END : Create users_type and users table --