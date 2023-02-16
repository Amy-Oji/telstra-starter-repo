package au.com.telstra.simcardactivator.dto;

import lombok.Data;
@Data
public class SimCardDTO {
    private String iccid;
    private String customerEmail;
    private boolean active;
}
