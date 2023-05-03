# Getting Started

### Start the backend
* In the file src/main/resources/application.properties, there is a property representing the api key to access the openexchangerates service.
* The property is "api.key.openexchangerates". Please paste a valid openexchangerates api key here for backend to work correctly.
* Same must be done in the file src/test/resources/application-test.properties.

### Start the frontend
* Open CMD terminal and navigate to the frontend directory
* Run "npm install"
* Run "npm start"
* This will start a react app at localhost:3000

### Pre-requisites
 * jdk >= 17
 * maven >= 3.6
 * docker
 
### Build Code

* mvn clean install

### Run Test

* mvn test

### Deploy

* docker build . -t smartrecruitingapp:v1.0.0
* docker run -d -p 8080:8080 smartrecruitingapp:v1.0.0

### App Url

* http://localhost:8080
