package com.ftn.studentservice.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Student {

    @Id
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "id")
    @MapsId
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
