package com.ftn.studentservice.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectCreationDTO {

    @JsonProperty("code")
    public String code;

    @JsonProperty("name")
    public String name;

    @JsonProperty("semester")
    public String semester;

    @JsonProperty("desc")
    public String description;

    @JsonProperty("ects")
    public Integer ECTS;

    @JsonProperty("major_id")
    public Long majorId;

    @JsonProperty("syllabus_id")
    public Long syllabusId;

}
