package au.com.telstra.simcardactivator.payload;

import java.util.Objects;

public class SIMActivationPayLoad {
    String iccid;
    String customerEmail;

    public String getIccid() {
        return iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public String toString() {
        return "SIMActivationPayLoad{" +
                "iccid='" + iccid + '\'' +
                ", email='" + customerEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SIMActivationPayLoad)) return false;
        SIMActivationPayLoad that = (SIMActivationPayLoad) o;
        return Objects.equals(getIccid(), that.getIccid()) && Objects.equals(getCustomerEmail(), that.getCustomerEmail());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getIccid(), getCustomerEmail());
    }
}
