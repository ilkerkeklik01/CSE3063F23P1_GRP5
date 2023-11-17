package Data;

import Domain.Advisor;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvisorDto extends LecturerDto {
    public Collection<String> studentNumbers;
    public Collection<String> registrationNumbers;

    public AdvisorDto(Advisor advisor){

        super(advisor);

        this.studentNumbers = advisor.getStudentNumbers();
        this.registrationNumbers = advisor.getRegistrationNumbers();

    }

    public AdvisorDto(){}
}
