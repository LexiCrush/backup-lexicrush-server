# backup-lexicrush-server
Backup source code for the server-side implementation of Lexicrush app.
Spring Boot application and is responsible for generating questions, managing user sessions, and providing a REST API for the client-side application.

## Prerequisites
* Java 8
* Maven
* SQLite

## Launch Locally
1. Clone the repository:
```
git clone https://github.com/<your username>/backup-lexicrush-server.git
```

2. Build the project using Maven:
```
mvn clean package
```

3. Run the Spring Boot application:
```
mvn spring-boot:run
```

4. The application will now be running at http://localhost:8080.

## Usage
The application provides a REST API for the client-side application. The API can be accessed at http://localhost:8080/randq and http://localhost:8080/checkans.

The randq endpoint will return a randomly generated question that the user can answer. The checkans endpoint will receive the user's answer and check if it is correct.
