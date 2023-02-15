package au.com.telstra.simcardactivator.payload;

import java.util.Objects;

public class SIMActivationDTO {
    String iccid;

    public SIMActivationDTO(String iccid) {
        this.iccid = iccid;
    }

    public SIMActivationDTO() {
    }

    public String getIccid() {
        return iccid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SIMActivationDTO)) return false;
        SIMActivationDTO that = (SIMActivationDTO) o;
        return Objects.equals(getIccid(), that.getIccid());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getIccid());
    }

    @Override
    public String toString() {
        return "SIMActivationDTO{" +
                "iccid='" + iccid + '\'' +
                '}';
    }


}
