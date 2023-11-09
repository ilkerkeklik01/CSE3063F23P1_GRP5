package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaffIDGenerator extends IDGenerator {
        
    public StaffIDGenerator(){
        super();
        this.setDigitCount(8);
        this.setIdPrefix("100");
        this.setIdSuffix("");
        this.setUsedIDs(getUsedIDsByOtherStaffs());
    }

    private List<String> getUsedIDsByOtherStaffs(){

        Collection<Staff> allStaff =  Department.getAllStaffs();
        List<String> alreadyUsedIDs = new ArrayList<>();

        for (Staff staff : allStaff) {
            alreadyUsedIDs.add(staff.getStaffNo());
        }
        return alreadyUsedIDs;
    }
}
