package au.com.telstra.simcardactivator.dto;

import lombok.Data;

@Data
public class SimCardActivationPayLoad {
    private String iccid;
    private String customerEmail;
}
