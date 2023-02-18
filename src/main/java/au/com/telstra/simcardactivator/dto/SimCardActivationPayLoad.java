package au.com.telstra.simcardactivator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimCardActivationPayLoad {
    private String iccid;
    private String customerEmail;
}
