package Domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Staff extends Person {

    private String staffNo;

    public Staff(String FName, String LName, Date birthdate, String staffNo) {
        super(FName, LName, birthdate);
        setStaffNo(staffNo);
    }

    public Staff(){}

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffNo='" + staffNo + '\'' +
                "} " + super.toString();
    }
}
