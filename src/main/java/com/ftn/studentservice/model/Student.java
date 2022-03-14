package com.ftn.studentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Data
@NoArgsConstructor
//End of lombok
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private User user;

    @Min(0)
    private Double funds;

    @Min(1)
    @Column(nullable = false)
    private Integer semester;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private Set<Document> documents;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Responsibility> responsibilities;
}
