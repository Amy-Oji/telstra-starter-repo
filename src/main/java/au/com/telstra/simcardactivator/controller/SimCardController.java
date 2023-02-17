package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.SimCardActivationPayLoad;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sim-card/")
public class SimCardController {
    private final SimCardService simCardService;
    @Autowired
    public SimCardController(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    @PostMapping("activate")
    public ResponseEntity<?> checkActivate(@RequestBody SimCardActivationPayLoad simCardActivationPayLoad){

        return ResponseEntity.ok(simCardService.callSimCardActuatorService(simCardActivationPayLoad));
    }

    @GetMapping("get-customer/{sim-card-id}")
    public ResponseEntity<?> getCustomerBySimCardId(@PathVariable("sim-card-id") long simCardId) throws Exception {

        return ResponseEntity.ok(simCardService.getSimCardDetailsById(simCardId));
    }
}
