package com.ftn.studentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
//End of lombok
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Code must not be left out!")
    @NotEmpty(message = "Code must not be left out!")
    @NotNull(message = "Code must not be left out!")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Name must not be left out!")
    @NotEmpty(message = "Name must not be left out!")
    @NotNull(message = "Name must not be left out!")
    @Column(nullable = false)
    private String name;


    @Max(message = "Semester must be lesser than 8", value = 8)
    @Min(message = "Semester must be greater than 1", value = 1)
    @NotNull
    @Column(nullable = false)
    private Integer semester;

    @NotBlank(message = "Desc must not be left out!")
    @NotEmpty(message = "Desc must not be left out!")
    @NotNull(message = "Desc must not be left out!")
    @Column(nullable = false)
    private String description;

    @Min(message = "ECTS must be greater than 1", value = 1)
    @NotNull(message = "ECTS must not be left out!")
    @Column(nullable = false)
    private Integer ECTS;


    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.REFRESH)
    private Set<Professor> professors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<ResponsibilityDefinition> responsibilityDefinitions = new LinkedHashSet<>();


    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "syllabus_id", nullable = false)
    private Syllabus syllabus;

}
