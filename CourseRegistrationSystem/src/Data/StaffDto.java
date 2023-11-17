package Data;

import Domain.Person;
import Domain.Staff;

public class StaffDto extends PersonDto {
    public String staffNo;

    public StaffDto(){}

    public StaffDto(Staff staff){
        super(staff);
        this.staffNo = staff.getStaffNo();
    }
}


