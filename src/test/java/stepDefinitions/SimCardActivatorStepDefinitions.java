package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.SimCardActivationPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    private static TestRestTemplate restTemplate;
    private static final String activationUrl = "http://localhost:8080/api/v1/sim-card/activate";
    private static final String getByCardIdUrl = "http://localhost:8080/api/v1/sim-card/get-customer/{sim-card-id}";

    @Autowired
    public SimCardActivatorStepDefinitions(TestRestTemplate restTemplate) {
        SimCardActivatorStepDefinitions.restTemplate = restTemplate;
    }

    static String isSimActivated(String iccid) {
        SimCardActivationPayLoad simCardActivationPayLoad = new SimCardActivationPayLoad(iccid, "customer@email.com");
        restTemplate.postForObject(activationUrl, simCardActivationPayLoad, String.class);

        Map<String, Long> params = new HashMap<>();
        String k = "sim-card-id";
        if (iccid.equals("1255789453849037777")) {
            params.put(k, 1L);
        } else {
            params.put(k, 2L);
        }

        SimCardDTO simCardDTO = restTemplate.getForObject(getByCardIdUrl, SimCardDTO.class, params);

        return simCardDTO.isActive() ? "true" : "false";
    }

    public static class Stepdefs {
        private String iccid;
        private String actualAnswer;

        @Given("SIM card iccid is {string}")
        public void simCardIccidIs(String iccid) {
            this.iccid = iccid;
        }

        @When("asked if SIM card is activated")
        public void askedIfSIMCardIsActivated() {
            actualAnswer = SimCardActivatorStepDefinitions.isSimActivated(iccid);
        }

        @Then("return this {string}")
        public void returnThis(String expectedAnswer) {
            assertEquals(expectedAnswer, actualAnswer);
        }
    }
}