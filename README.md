# Rest Service For String Operation

### To verify the build

To build the project, execute the below command from root folder
```
./gradlew build
```
It will produce the Jar and Code Coverage report in /build folder
```
Code Coverage Report -> /build/reports/jacoco/test/html/index.html
Test Results Report  -> /build/test-results/test
```

### Start project as SpringBoot using Gradle Wrapper

To start the project as SpringBoot application using gradle wrapper, simply run the below command
```
./gradlew bootRun
```
Now you can see the application status in the logs and able to test the actual endpoints
Added the swagger-ui for the API documentation

* Application URL - http://localhost:8080
* Swagger URL     - http://localhost:8080/swagger-ui/


Once the service started, the endpoint will be available at `localhost:8080` & swagger-ui available at `http://localhost:8080/swagger-ui/`, so you can make request to the service endpoint

Giving a few examples,

```json
GET http://localhost:8080/v2/reply/11-kbzw9ru

{
    "data": "kbzw9ru"
}
```
```json
GET http://localhost:8080/v2/reply/12-kbzw9ru

{
    "data": "5a8973b3b1fafaeaadf10e195c6e1dd4"
}
```
You can still use the existing endpoint

### Run the Application as Jar
Execute the below command from root folder
```
gradle clean build
```
It will produce the Jar and Code Coverage report in /build folder
```
Jar Path -> /build/libs/rest-service-2.0-SNAPSHOT.jar
```
Execute the jar using below command, replace the password while executing
```
java -jar /build/libs/rest-service-2.0-SNAPSHOT.jar
```

### For Adding New Rules
* Introduce the new RuleType in the RuleType ENUM
* Add the Implementation for the New RuleType and implements the IRule interface
* Implementation class should return the ruleType as the new ruleType added in the RuleTypeEnum
* You are good to go now

### Technologies

* Java 8
* Spring Boot 2.5.3
* Lombok
* Swagger
* Mockito
* Jacoco