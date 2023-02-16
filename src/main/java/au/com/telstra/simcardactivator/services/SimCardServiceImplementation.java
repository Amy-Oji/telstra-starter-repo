package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.dto.SimCardDTO;
import au.com.telstra.simcardactivator.entities.SimCardEntity;
import au.com.telstra.simcardactivator.dto.SimCardActuatorPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardActivationPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SimCardServiceImplementation implements SimCardService {

    private final RestTemplate restTemplate;

    private final SimCardRepository simCardRepository;
    private final String url;



    @Autowired
    public SimCardServiceImplementation(RestTemplate restTemplate, SimCardRepository simCardRepository) {
        this.restTemplate = restTemplate;
        this.simCardRepository =  simCardRepository;
        this.url = "http://localhost:8444/actuate";

    }

    public SimCardActuatorResponse callSimCardActuatorService(SimCardActivationPayLoad simCardActivationPayLoad){

        SimCardActuatorPayLoad simCardActuatorPayLoad = new SimCardActuatorPayLoad(simCardActivationPayLoad.getIccid());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SimCardActuatorPayLoad> entity = new HttpEntity<>(simCardActuatorPayLoad, headers);

        SimCardActuatorResponse response = restTemplate.postForObject(url, entity,  SimCardActuatorResponse.class);

        Logger logger = LoggerFactory.getLogger(getClass());

        assert response != null;

        SimCardEntity simCardEntity = new SimCardEntity();
        BeanUtils.copyProperties(simCardActivationPayLoad, simCardEntity);

        simCardEntity.setActive(response.isSuccess());
        simCardRepository.save(simCardEntity);
        System.out.println(simCardEntity);

        logger.info("Response From Actuator MicroService ==> {}", response.isSuccess());

        return response;
    }

    @Override
    public SimCardDTO getCustomerById(long id) {
        SimCardEntity customer = simCardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("customer with id number " + id + " not found"));
        SimCardDTO simCardDTO = new SimCardDTO();
        BeanUtils.copyProperties(customer, simCardDTO);
        return simCardDTO;
    }

}
