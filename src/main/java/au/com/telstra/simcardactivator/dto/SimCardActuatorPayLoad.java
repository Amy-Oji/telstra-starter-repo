package au.com.telstra.simcardactivator.dto;

import lombok.Data;

@Data
public class SimCardActuatorPayLoad {
    private String iccid;
    public SimCardActuatorPayLoad(String iccid ) {
        this.iccid = iccid;
    }
}
