package au.com.telstra.simcardactivator.entities;

import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
public class SimCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String iccid;

    private String customerEmail;

    private boolean active;

    public String getIccid() {
        return iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
