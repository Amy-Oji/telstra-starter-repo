package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.SimCardActuatorPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    private static TestRestTemplate restTemplate;
    private static final String url = "http://localhost:8444/actuate";
    @Autowired
    public SimCardActivatorStepDefinitions(TestRestTemplate restTemplate) {
        SimCardActivatorStepDefinitions.restTemplate = restTemplate;
    }

    static String isSimActivated(String iccid) {
        SimCardActuatorPayLoad simCardActuatorPayLoad = new SimCardActuatorPayLoad(iccid);
        SimCardActuatorResponse response = restTemplate.postForObject(url, simCardActuatorPayLoad,  SimCardActuatorResponse.class);
        return response.isSuccess()? "true" : "false";
    }

    public static class Stepdefs {
        private String iccid;
        private String actualAnswer;

        @Given("iccid is {string}")
        public void iccidIs(String iccid) {
            this.iccid = iccid;
        }

        @When("asked if SIM is activated")
        public void askedIfSIMIsActivated() {
            actualAnswer = SimCardActivatorStepDefinitions.isSimActivated(iccid);
        }

        @Then("return this {string}")
        public void returnThis(String expectedAnswer) {
            assertEquals(expectedAnswer, actualAnswer);
        }

    }


}