package com.ftn.studentservice.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    @Min(0)
    private Double funds;

    @Column
    private Integer semester;

    @Column
    private LocalDate yearOfEnrollment;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Enrollment> enrollments = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true)
    private Set<Document> documents;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Responsibility> responsibilities;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Exam> exams = new java.util.LinkedHashSet<>();
}
