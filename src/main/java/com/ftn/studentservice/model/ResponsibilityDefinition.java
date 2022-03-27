package com.ftn.studentservice.model;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class ResponsibilityDefinition {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Enumerated(EnumType.STRING)
    @Column
    private ResponsibilityType responsibilityType;


    @OneToMany(mappedBy = "responsibilityDefinition", orphanRemoval = true)
    private Set<Responsibility> responsibilities;

}
