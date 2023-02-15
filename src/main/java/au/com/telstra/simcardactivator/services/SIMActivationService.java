package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.payload.SIMActivationDTO;

public interface SIMActivationService {
    String callSimCardActuatorService(SIMActivationDTO simActivationDTO);
}
