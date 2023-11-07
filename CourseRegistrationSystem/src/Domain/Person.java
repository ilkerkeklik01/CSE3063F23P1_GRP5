package Domain;

import java.util.Date;
import java.util.UUID;


public abstract class Person {

    //private UUID id = UUID.randomUUID();
    private String fName;
    private String lName;
    private Date birthdate;

    public Person(String FName, String LName, Date birthdate) {
        setFName(FName);
        setLName(LName);
        setBirthdate(birthdate);
    }

    public Person(){

    }

    public String getFName() {
        return fName;
    }

    public void setFName(String FName) {
        this.fName = FName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String LName) {
        this.lName = LName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        birthdate = birthdate;
    }



}
