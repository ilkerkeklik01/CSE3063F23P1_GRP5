package Data;

import Domain.Lecturer;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class LecturerDto extends StaffDto {
    public Collection<String> courseCodes;

    public LecturerDto(Lecturer lecturer){
        super(lecturer);
        this.courseCodes = lecturer.getCourseIds();

    }


    public LecturerDto(){}


}
