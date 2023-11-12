Connecting database using XAMPP:
1. create database 'rizq_nasional' in phpmyadmin
2. import file 'rizq_nasional.sql'
3. don't forget in application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/rizq_nasional -> uncomment this
    spring.datasource.url=jdbc:mysql://mysql-container:3306/rizq_nasional -> comment this
4. make sure your xampp is running.
5. run your springbot project

Run this project using docker:
1. docker build -t rizq-api-image .
2. docker compose up --build
3. don't forget in application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/rizq_nasional -> comment this
    spring.datasource.url=jdbc:mysql://mysql-container:3306/rizq_nasional -> uncomment this
4. your springbot project running on docker