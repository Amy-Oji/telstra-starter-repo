# Telstra Starter Repo :bird:

This repo has everything you need to get started on the Telstra program!

#### Information about the Application
In sum, the application is REST API that receives requests of SIM cards details to be activated, 
submits the details for activation then returns response of whether the SIM card was successfully activated or not 
while persisting both the SIM card details and the response in H2 Database.

The application was tested using Cucumber which is a software testing process that deals with an application's behavior.

Ran the application on SonarQube to ensure that the codebase is bugs-free and clean. Got an A in each of the listed categories on the very first run.
SonarQube is a self-managed, automatic code review tool that systematically helps you deliver clean code.
A screenshot of the SonarQube report can be found in the static folder -> telstra-starter-repo/src/main/resources/static


The program is made up of two microservices: The *Activator* and *Actuator* microservices.

The Activator microservice a REST API with two endpoints: 
"api/v1/sim-card/activate" -> the endpoint to send requests for SIM card activation
"api/v1/sim-card/get-customer/{sim-card-id}" -> the endpoint to get SIM card customer details by ID


The *"activate"* endpoint is a POST Request that takes a JSON payload containing the SIM ICCID and the customer’s email address with the following structure:
{
"iccid": string,
“customerEmail”: string
}
It then submits a POST Request to the *Actuator* microservice which is responsible for the actual activation of SIM cards at this endpoint =>  “http://localhost:8444/actuate”
Actuator microservice takes a JSON payload with the following structure:
{
"iccid": string
}
And returns a response with a JSON body of the form:
{
"success": boolean
}
If the SIM card was successfully activated, the value of "success" will be true, else it returns false.

The details of the SIM card and the response from the Actuator microservice gets persisted by the Activation Microservice 
then the "activate" endpoint returns the response gotten from the Actuator microservice. 


The *"get-customer/{sim-card-id}"* endpoint is a GET request that returns the details of the SIM card that corresponds to 
the "sim-card-id" passed in as path variable in the request. If it cannot find the corresponding details, it throws an error.


__Note:__
The actuator is microservice was developed by the Telstra team and supplied as a JAR file in this path: telstra-starter-repo/services. 
It can be run with a Java 11 JRE. Executing this programme starts a local server on port 8444 which accepts post requests to the endpoint “http://localhost:8444/actuate”.

