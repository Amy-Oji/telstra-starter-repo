package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.payload.SIMActivationDTO;
import au.com.telstra.simcardactivator.payload.SIMActivationPayLoad;
import au.com.telstra.simcardactivator.services.SIMActivationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/SIMActivation/")
public class SIMActivationController {

    private final RestTemplate restTemplate;

    private final SIMActivationService simActivationService;
    @Autowired
    public SIMActivationController(RestTemplate restTemplate, SIMActivationService simActivationService) {
        this.restTemplate = restTemplate;
        this.simActivationService = simActivationService;
    }


    @PostMapping("check-activation")
    public ResponseEntity<?> checkActivate(@RequestBody SIMActivationPayLoad simActivationPayLoad){

        SIMActivationDTO simActivationDTO = new SIMActivationDTO();
        BeanUtils.copyProperties(simActivationPayLoad, simActivationDTO);

        return ResponseEntity.ok(simActivationService.callSimCardActuatorService(simActivationDTO));
    }
}
