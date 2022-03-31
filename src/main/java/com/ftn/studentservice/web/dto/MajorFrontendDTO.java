package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Major;

import java.io.Serializable;

public class MajorFrontendDTO implements Serializable {

    private String name;
    private Integer duration;
    private Integer totalETCS;

    public MajorFrontendDTO() {
    }

    public MajorFrontendDTO(Major major) {
        this.name = major.getName();
        this.duration = major.getDuration();
        this.totalETCS = major.getTotalETCS();
    }

    public MajorFrontendDTO(String name, Integer duration, Integer totalETCS) {
        this.name = name;
        this.duration = duration;
        this.totalETCS = totalETCS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTotalETCS() {
        return totalETCS;
    }

    public void setTotalETCS(Integer totalETCS) {
        this.totalETCS = totalETCS;
    }
}
