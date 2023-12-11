package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegistrationIDGenerator extends IDGenerator {
    public RegistrationIDGenerator(){
        super();
        this.setDigitCount(5);
        this.setIdPrefix("R");
        this.setIdSuffix("");
        this.setUsedIDs(getUsedIDsByOtherRegistrations());
    }

    private List<String> getUsedIDsByOtherRegistrations(){
        Collection<Registration> allRegistrations = Department.getInstance().getAllRegistrations();
        List<String> alreadyUsedIDs = new ArrayList<>();
       
        for (Registration registration: allRegistrations) {
            alreadyUsedIDs.add(registration.getRegistrationNo());
        }
        return alreadyUsedIDs;
    }
}
