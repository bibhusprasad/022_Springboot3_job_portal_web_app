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

-- START : Create recruiter_profile, job_seeker_profile and skills table --
CREATE TABLE `recruiter_profile` (
    `user_account_id` int NOT NULL,
    `city` varchar(255) DEFAULT NULL,
    `company` varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `profile_photo` varchar(64) DEFAULT NULL,
    `state` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_account_id`),
    CONSTRAINT `FK42q4eb7jw1bvw3oy83vc05ft6` FOREIGN KEY (`user_account_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `job_seeker_profile` (
    `user_account_id` int NOT NULL,
    `city` varchar(255) DEFAULT NULL,
    `country` varchar(255) DEFAULT NULL,
    `employment_type` varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `profile_photo` varchar(255) DEFAULT NULL,
    `resume` varchar(255) DEFAULT NULL,
    `state` varchar(255) DEFAULT NULL,
    `work_authorization` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_account_id`),
    CONSTRAINT `FKohp1poe14xlw56yxbwu2tpdm7` FOREIGN KEY (`user_account_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `skills` (
    `id` int NOT NULL AUTO_INCREMENT,
    `experience_level` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `years_of_experience` varchar(255) DEFAULT NULL,
    `job_seeker_profile` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKsjdksau8sat30c00aqh5xf2wh` (`job_seeker_profile`),
    CONSTRAINT `FKsjdksau8sat30c00aqh5xf2wh` FOREIGN KEY (`job_seeker_profile`) REFERENCES `job_seeker_profile` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END : Create recruiter_profile, job_seeker_profile and skills table --