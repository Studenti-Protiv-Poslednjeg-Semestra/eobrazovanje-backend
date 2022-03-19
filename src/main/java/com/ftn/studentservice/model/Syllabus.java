package com.ftn.studentservice.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Syllabus {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate yearOfCreation;

    @OneToMany(mappedBy = "syllabus", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Subject> subjects;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

}
