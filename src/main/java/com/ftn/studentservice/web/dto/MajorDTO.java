package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Major;

import java.io.Serializable;

public class MajorDTO implements Serializable {

    private Long id;
    private String name;
    private Integer duration;
    private Integer totalETCS;

    public MajorDTO() {
    }

    public MajorDTO(Major major) {
        this.id = major.getId();
        this.name = major.getName();
        this.duration = major.getDuration();
        this.totalETCS = major.getTotalETCS();
    }

    public MajorDTO(Long id, String name, Integer duration, Integer totalETCS) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.totalETCS = totalETCS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
