package Domain;

import java.util.Date;


public abstract class Person implements Searchable{

    private String personId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
