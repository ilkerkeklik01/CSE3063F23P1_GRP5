package Domain;

import java.util.List;
import java.util.Random;

public class IDGenerator {
    
    private String id;
    private String idPrefix="";
    private String idSuffix="";
    private int digitCount;
    private List<String> usedIDs;
    private boolean isIdUnique;
    
    //#region Properties

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPrefix() {
        return idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public String getIdSuffix() {
        return idSuffix;
    }

    public void setIdSuffix(String idSuffix) {
        this.idSuffix = idSuffix;
    }

    public int getDigitCount() {
        return digitCount;
    }

    public void setDigitCount(int digitCount) {
        this.digitCount = digitCount;
    }

    public List<String> getUsedIDs() {
        return usedIDs;
    }

    public void setUsedIDs(List<String> usedIDs) {
        this.usedIDs = usedIDs;
    }

    public boolean isIdUnique() {
        return isIdUnique;
    }

    public void setIdUnique(boolean isIdUnique) {
        this.isIdUnique = isIdUnique;
    }
    //#endregion Properties

    private String generateID(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(idPrefix);

        do {
            for(int i = idPrefix.length(); i < digitCount; i++){
                sb.append(random.nextInt(10));
            }
        } while(usedIDs.contains(sb.toString()));
        
        sb.append(idSuffix);

        id = sb.toString();       
        return id;
    }

    public String generateNewID(IDType idType){
        IDGenerator idGenerator = null;

        switch(idType){
            case StudentID:
                idGenerator = new StudentIDGenerator();
                break;
            case StaffID:
                idGenerator = new StaffIDGenerator();
                break;
            case PersonID:
                idGenerator = new PersonIDGenerator();
                break;
            case RegistrationID:
                idGenerator = new RegistrationIDGenerator();
                break;
            default:
                throw new IllegalArgumentException("Invalid ID type");
        }
        
        String generatedID = idGenerator.generateID();
        System.out.println(idType + " Generated ID: " + generatedID);
        return generatedID;        
    }


    
    
}
