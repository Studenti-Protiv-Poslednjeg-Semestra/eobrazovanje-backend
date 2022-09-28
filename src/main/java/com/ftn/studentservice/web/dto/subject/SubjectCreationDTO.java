package com.ftn.studentservice.web.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ftn.studentservice.model.ResponsibilityType;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectCreationDTO {

    @JsonProperty("code")
    @NotBlank(message = "Subject's must contain Code!")
    public String code;

    @JsonProperty("name")
    @NotBlank(message = "Subject must contain Name!")
    public String name;

    @JsonProperty("semester")
    @NotNull(message = "Subject must contain semester!")
    @Min(value = 1, message = "Subject's semester must be >= 1")
    public Integer semester;
    @NotBlank(message = "Subject must contain Description!")
    public String description;

    @JsonProperty("ects")
    @NotNull(message = "Subject must contain ECTS!")
    @Min(value = 1, message = "Subject's ECTS must be >= 1")
    @Max(value = 60, message = "Subject's ECTS must be <= 60")
    public Integer ECTS;

    @NotNull(message = "Subject must contain Syllabus id!")
    public Long syllabusId;

    public List<ResponsibilityType> responsibilityDefinitions = new ArrayList<>();
}
