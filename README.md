# GenesysCodeChallenge

## Tools, libs and IDE

- IDE: Spring tool suit 4
- Tools: Maven, Swagger
- Depends on: Java 8 + Spring boot 2.4.3 + Mysql8
- Libs: please check maven pom.xml

## Build and unit test

- mvn clean install
- mvn clean test

## API enpoints management

- Managed by swagger 2, please visit http://[YOUR_HOST]//swagger-ui.html
- swagger.json

## Deployment

- local environment: localhost
- aws: http://userbuildingelevatoraws-env.eba-tfikzzna.eu-west-1.elasticbeanstalk.com/

## Feature and API

| Feature  | API | Sample payload | return | Sample response |
| --- | --- | --- | --- | --- |
| Add a user | GET /user | N/A | json for new user id |{"id" : "string"}  |
| Update a user - change name, modify buildings belongs to | PUT /user/{id} | {"buildingIds": ["string"],"name": "string"} | json for existing user id | {"id" : "String"}  |
| Find a building for a user | GET /building | N/A | json for building | [{"elevatorIds": ["string"],"id": "string","location": "string","name": "string"}] |
| Get status of all elevators in a building for a user | GET /user/{id}/building; GET /building/{id}/elevator-status | N/A | json with elevator data |[{"currentFloorId": 0,"id": "string","state": "UP"}] |
| User summons an elevator | PUT elevator/{id} | {"currentFloorId": 0,"id": "string","state": "UP"} | elevator with updated status | {"currentFloorId": 0,"id": "string","state": "UP"} |
| User select a floor | PUT elevator/{id} | {"currentFloorId": 0,"id": "string","state": "UP"} | elevator with updated status | {"currentFloorId": 0,"id": "string","state": "UP"}|


