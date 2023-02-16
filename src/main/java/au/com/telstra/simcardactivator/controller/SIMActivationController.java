package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.SimCardActivationPayLoad;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/sim-activation/")
public class SIMActivationController {

    private final RestTemplate restTemplate;

    private final SimCardService simCardService;
    @Autowired
    public SIMActivationController(RestTemplate restTemplate, SimCardService simCardService) {
        this.restTemplate = restTemplate;
        this.simCardService = simCardService;
    }

    @PostMapping("activate")
    public ResponseEntity<?> checkActivate(@RequestBody SimCardActivationPayLoad simCardActivationPayLoad){

        return ResponseEntity.ok(simCardService.callSimCardActuatorService(simCardActivationPayLoad));
    }

    @GetMapping("get-customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") long id){

        return ResponseEntity.ok(simCardService.getCustomerById(id));
    }
}
