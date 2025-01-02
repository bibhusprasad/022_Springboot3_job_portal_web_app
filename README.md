###### 022_Springboot3_job_portal_web_app
###### [Github Repo URL](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app)

# Job portal web application using Java and Springboot3 

## 1. Registration
### 1.1 Setup Spring Boot Project
* [setup springboot project](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/7e64e0096217dbe4eccb6539a214367804368e74)

### 1.2 Add Project Template files (HTML, JS and CSS)
Web jars for our project 

**bootstrap**       -> CSS framework for HTML page layout / positioning  
**jquery**          -> JavaScript framework for HTML page processing  
**font-awesome**    -> Collections of fonts, icons and images  
**webjars-locator** -> Support file for locating assets in JAR files
* [add project template files (HTML, JS and CSS)](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/92877a605bedbf7564d7c2cfd33bc731a6c8b401)
* [html and js refactored](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/06b167e4be104ee0e446fd912578a3e43eddfbc9)

### 1.3 Database Entities for Users, UserTypes
* [Database Entities for Users, UserTypes](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/613d442f4c96b5e49b287d4d4ce38e7344c7bb16)

### 1.4 User Registration: Repositories and Controller
* [registration repositories and controller part 1](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/8a4fd05e7ba33dfb62e829e90ac23b6efda1ef79)
* [registration repositories and controller part 2](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/84299918f2673482e5b18d6b1c26a9a2b324fb65)

### 1.5 Bug fix duplicate Email for registration
* [block duplicate email for registration](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/327bd1eb15f9f8f136ba8171eaaf65e46962fcb9)
* [refactored html code and remove unused imports](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/5caeac4cdcde1b5c9da2cccb44c0a261ff38d062)

### 1.6 Create profile for recruiters and job seekers and add skills for job seekers
* [create profile for recruiters and job seekers and add skills for job seekers](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/a2cc4fffe2af07f11babcec169166da0c664ebe7)
* [refactored ui enhanced based on job seeker and recruiter user](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/187fe3f995a24dbb9d41caf402e016319cecf22e)

## 2. Security Login and Logout
### 2.1 Configure Spring Security and develop custom user authentication and authorization
* [configure spring security and develop custom user authentication and authorization](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/fd044bee9ea37d4e378ea93286ab9805ab9aeab3)

### 2.2 Custom Authentication success handler and integrate dashboard with current logged user
* [custom Authentication success handler and integrate Dashboard with Current Logged in User](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/df979a391c9e6da2dd4931f0fdd7d78ada299bd4)

### 2.3 Add login logout request mapping to controller
* [add login logout request mapping to controller](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/509f552ac657ab5d271e8b1902afc1c1e6c74769)
* [refactored ui enhanced based on job seeker and recruiter user](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/8764f75488d692d95104ee61ae67a99d7017efaa)

## 3. Recruiter Profile
### 3.1 Create requiter profile controller and service
* [create requiter profile controller and service](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/a2cc4fffe2af07f11babcec169166da0c664ebe7)
* [refactor controller, service and repository based on hierarchy](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/73af95ea4bc37fb20c823e153073d9498e7b6a89)

### 3.2 Add support for file upload and update dash board to display recruiter profile
* [add support for file upload and update dash board to display recruiter profile](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/a85677c3922d8743e3be5f82640768db58a7bf38)

### 3.3 Add logging framework slf4j and Custom Exception class
* [add logging framework slf4j](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/5c064a5584aca31b7d84a15296b73272d5f01cae)
* [add custom unchecked exception class](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/dc66025fba326bace8fdb4138117669c4ebea7e6)

## 4. Recruiter Post New Job
### 4.1 Create JPA entities for Recruiter job post activities
* [create JPA entities for recruiter job post activities](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/d26e1fe7c19d7ec16f510a9c230a8a94f6cfd566)
* [refactored entities to use lombok framework](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/a4c52c4e1c5e6e7e056e1b8b362c7ffaa0c8c775)

### 4.2 Integrate HTML form for job posting
* [integrate HTML form for job posting](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/e68c86988009b16f6e4b782378768e7df2e06678)

## 5. Recruiter Dashboard
### 5.1 Recruiter Dashboard display and retrieve jobs
* [recruiter dashboard display and retrieve jobs](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/66883da4efd21faaed651d5c088a94789bc36955)

### 5.2 Edit a existing job
* [edit a existing job](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/bbe7e8a84f1f7b2564c329801216c0fef4fb58de)

## 6. Job Candidate profile
### 6.1 Create Job Seeker Profile controller and HTML file
* [create job seeker profile controller and html file](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/e1693f9d8d2c8039d1b9904903056232c2ceee4a)

### 6.2 Create Job Seeker Profile image update and resume upload
* [add support to job seeker profile image and resume upload](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/dab229b2d803d40b70fb4779c40d2813c7e64988)

## 7. Job Candidate Dashboard and Apply for job
### 7.1 Job Candidate Controller for Searching and Applying for Job Part 1
* [job candidate controller for searching and applying for job part1](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/1a357b895b0d11f15bb36c0912b829835ef1b2c4)

### 7.2 Job Candidate Controller for Searching and Applying for Job Part 2
* [job candidate controller for searching and applying for job part2](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/9d499ffe2814a310ebec521dff28788085b5ad68)

### 7.3 Job Candidate Controller for Searching and Applying for Job Part 3
* [job candidate controller for searching and applying for job part3](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/7a0eaeaf1b1990501377de7c428d43e05f18efed)

## 8. Job Candidate Save a job
### 8.1 Job Candidate Save a job
* [job candidate save a job](https://github.com/bibhusprasad/022_Springboot3_job_portal_web_app/commit/29c91fea98f9b93d2f3caaae8cbc840d2d551d61)

## 9. Recruiter Download candidate resume
### 9.1 View Applied Candidate profile in recruiter page
* [view applied candidate profile in recruiter page]()