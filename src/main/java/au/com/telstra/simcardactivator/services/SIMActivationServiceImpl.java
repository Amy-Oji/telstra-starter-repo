package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.payload.SIMActivationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SIMActivationServiceImpl implements SIMActivationService {

    private final RestTemplate restTemplate;

    @Autowired
    public SIMActivationServiceImpl(RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;

    }

    public String callSimCardActuatorService(SIMActivationDTO simActivationDTO){

        final String url = "http://localhost:8444/actuate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SIMActivationDTO> entity = new HttpEntity<>(simActivationDTO,headers);

        String response = restTemplate.postForObject(url, entity,  String.class);

        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Response JSON From Actuator MicroService ==> {}", response);

        return response;
    }

}
