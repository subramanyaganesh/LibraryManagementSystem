package com.project.LibraryManagement.Model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
@Table(name = "contributor")
public class Contributor {
    @Id
    @SequenceGenerator(name = "contributor_generator", allocationSize = 1, initialValue = 3100)
    @GeneratedValue(generator = "contributor_generator")
    Long contributorId;
    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;
    @Column(name = "firstName", length = 250, nullable = false)
    String firstName;
    @Column(name = "lastName", length = 250)
    String lastName;

    public Contributor() {
    }

    @Builder
    public Contributor(String emailId, String firstName, String lastName) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "contributorSet")
    private Set<Magazine> Magazines=new HashSet<>();


}
