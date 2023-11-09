package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonIDGenerator extends IDGenerator {
    
    public PersonIDGenerator(){
        super();
        this.setDigitCount(8);
        this.setIdPrefix("999");
        this.setIdSuffix("");
        this.setUsedIDs(GetUsedIDsByOtherPeople());
    }

    private List<String> GetUsedIDsByOtherPeople(){

        Collection<Person> allPersons = Department.getAllPeople();
        List<String> alreadyUsedIDs = new ArrayList<>();

        for (Person person : allPersons) {
            alreadyUsedIDs.add(person.getPersonId());
        }
        
        return alreadyUsedIDs; 
    }
}
