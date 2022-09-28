package com.ftn.studentservice.model;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class ResponsibilityDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private ResponsibilityType responsibilityType;

    @OneToMany(mappedBy = "responsibilityDefinition", orphanRemoval = true)
    private Set<Responsibility> responsibilities = new LinkedHashSet<>();

}
