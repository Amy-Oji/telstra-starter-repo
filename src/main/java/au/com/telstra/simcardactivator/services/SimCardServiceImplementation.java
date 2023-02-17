package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.dto.SimCardDTO;
import au.com.telstra.simcardactivator.entities.SimCardEntity;
import au.com.telstra.simcardactivator.dto.SimCardActuatorPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardActivationPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        SimCardActuatorResponse response = restTemplate.postForObject(url, simCardActuatorPayLoad,  SimCardActuatorResponse.class);

        assert response != null;

        SimCardEntity simCardEntity = new SimCardEntity();
        BeanUtils.copyProperties(simCardActivationPayLoad, simCardEntity);
        simCardEntity.setActive(response.isSuccess());
        simCardRepository.save(simCardEntity);

        return response;
    }

    @Override
    public SimCardDTO getSimCardDetailsById(long simCardId) throws Exception {
        SimCardEntity customer = simCardRepository.findById(simCardId)
                .orElseThrow(()-> new Exception("Customer with id number " + simCardId + " not found"));

        SimCardDTO simCardDTO = new SimCardDTO();
        BeanUtils.copyProperties(customer, simCardDTO);

        return simCardDTO;
    }

}
