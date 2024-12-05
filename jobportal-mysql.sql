-- START : Create user if required --
-- Drop user first if they exist
DROP USER IF EXISTS 'jobportal'@'%';
-- Now create user with prop privileges
CREATE USER 'jobportal'@'localhost' IDENTIFIED BY 'jobportal';
GRANT ALL PRIVILEGES ON * . * TO 'jobportal'@'localhost';
-- END : Create user if required --

-- START : Create database if required --
DROP DATABASE  IF EXISTS `jobportal`;
CREATE DATABASE `jobportal`;
USE `jobportal`;
-- END : Create database if required --

-- START : Create users_type and users table --
CREATE TABLE `users_type` (
                              `user_type_id` int NOT NULL AUTO_INCREMENT,
                              `user_type_name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `users_type` VALUES (1,'Recruiter'),(2,'Job Seeker');

CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) DEFAULT NULL,
                         `is_active` bit(1) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `registration_date` datetime(6) DEFAULT NULL,
                         `user_type_id` int DEFAULT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
                         KEY `FK5snet2ikvi03wd4rabd40ckdl` (`user_type_id`),
                         CONSTRAINT `FK5snet2ikvi03wd4rabd40ckdl` FOREIGN KEY (`user_type_id`) REFERENCES `users_type` (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END : Create users_type and users table --