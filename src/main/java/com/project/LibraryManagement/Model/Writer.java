package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Writer {
    @Id
    @SequenceGenerator(name = "writer_generator", allocationSize = 1, initialValue = 200)
    @GeneratedValue(generator = "writer_generator")
    Long writerId;
    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;
    @Column(name = "firstName", length = 250, nullable = false, unique = true)
    String firstName;

    @Column(name = "lastName", length = 250, nullable = false, unique = true)
    String lastName;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Thesis> thesisSet=new HashSet<>();

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<TechnicalReport> technicalReportSet;

}
