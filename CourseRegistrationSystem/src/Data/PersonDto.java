package Data;

import Domain.Person;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PersonDto {
    public String personId;
    public String fName;
    public String lName;
    public Date birthdate;

    public PersonDto(Person person){
        this.personId = person.getPersonId();
        this.fName = person.getFName();
        this.lName = person.getLName();
        this.birthdate = person.getBirthdate();
    }

    public PersonDto(){}
}
