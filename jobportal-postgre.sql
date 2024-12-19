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
    user_id SERIAL PRIMARY KEY NOT null,
    email varchar(255) DEFAULT NULL,
    is_active boolean DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    registration_date timestamp(6) DEFAULT NULL,
    user_type_id int DEFAULT NULL,
    CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT FK5snet2ikvi03wd4rabd40ckdl FOREIGN KEY (user_type_id) REFERENCES users_type (user_type_id)
);

CREATE INDEX FK5snet2ikvi03wd4rabd40ckdl ON users (user_type_id);
-- END : Create users_type and users table --

-- START : Create recruiter_profile, job_seeker_profile and skills table --
CREATE TABLE recruiter_profile (
    user_account_id SERIAL PRIMARY KEY NOT NULL,
    city varchar(255) DEFAULT NULL,
    company varchar(255) DEFAULT NULL,
    country varchar(255) DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
    profile_photo varchar(64) DEFAULT NULL,
    state varchar(255) DEFAULT NULL,
    CONSTRAINT FK42q4eb7jw1bvw3oy83vc05ft6 FOREIGN KEY (user_account_id) REFERENCES users (user_id)
);

CREATE TABLE job_seeker_profile (
    user_account_id SERIAL PRIMARY KEY NOT NULL,
    city varchar(255) DEFAULT NULL,
    country varchar(255) DEFAULT NULL,
    employment_type varchar(255) DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
    profile_photo varchar(255) DEFAULT NULL,
    resume varchar(255) DEFAULT NULL,
    state varchar(255) DEFAULT NULL,
    work_authorization varchar(255) DEFAULT NULL,
    CONSTRAINT FKohp1poe14xlw56yxbwu2tpdm7 FOREIGN KEY (user_account_id) REFERENCES users (user_id)
);

CREATE TABLE skills (
    id SERIAL PRIMARY KEY NOT NULL,
    experience_level varchar(255) DEFAULT NULL,
    name varchar(255) DEFAULT NULL,
    years_of_experience varchar(255) DEFAULT NULL,
    job_seeker_profile int DEFAULT NULL,
    CONSTRAINT FKsjdksau8sat30c00aqh5xf2wh FOREIGN KEY (job_seeker_profile) REFERENCES job_seeker_profile (user_account_id)
);
CREATE INDEX FKsjdksau8sat30c00aqh5xf2wh ON skills (job_seeker_profile);
-- END : Create recruiter_profile, job_seeker_profile and skills table --

-- START : Create job_company, job_location and job_post_activity table --
CREATE TABLE job_company (
    id SERIAL PRIMARY KEY NOT NULL,
    logo varchar(255) DEFAULT NULL,
    name varchar(255) DEFAULT NULL
);

CREATE TABLE job_location (
    id SERIAL PRIMARY KEY NOT NULL,
    city varchar(255) DEFAULT NULL,
    country varchar(255) DEFAULT NULL,
    state varchar(255) DEFAULT NULL
);

CREATE TABLE job_post_activity (
    job_post_id SERIAL PRIMARY KEY NOT NULL,
    description_of_job varchar(10000) DEFAULT NULL,
    job_title varchar(255) DEFAULT NULL,
    job_type varchar(255) DEFAULT NULL,
    posted_date timestamp(6) DEFAULT NULL,
    remote varchar(255) DEFAULT NULL,
    salary varchar(255) DEFAULT NULL,
    job_company_id int DEFAULT NULL,
    job_location_id int DEFAULT NULL,
    posted_by_id int DEFAULT NULL,
    CONSTRAINT FK44003mnvj29aiijhsc6ftsgxe FOREIGN KEY (job_location_id) REFERENCES job_location (id),
    CONSTRAINT FK62yqqbypsq2ik34ngtlw4m9k3 FOREIGN KEY (posted_by_id) REFERENCES users (user_id),
    CONSTRAINT FKpjpv059hollr4tk92ms09s6is FOREIGN KEY (job_company_id) REFERENCES job_company (id)
);

CREATE INDEX FKpjpv059hollr4tk92ms09s6is ON job_post_activity (job_company_id);
CREATE INDEX FK44003mnvj29aiijhsc6ftsgxe ON job_post_activity (job_location_id);
CREATE INDEX FK62yqqbypsq2ik34ngtlw4m9k3 ON job_post_activity (posted_by_id);

-- END : Create job_company, job_location and job_post_activity table --