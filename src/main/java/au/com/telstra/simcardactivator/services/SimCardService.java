package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.dto.SimCardDTO;
import au.com.telstra.simcardactivator.dto.SimCardActivationPayLoad;
import au.com.telstra.simcardactivator.dto.SimCardActuatorResponse;

public interface SimCardService {
    SimCardActuatorResponse callSimCardActuatorService(SimCardActivationPayLoad simCardActivationPayLoad);

    SimCardDTO getCustomerById(long simCardId) throws Exception;

}
