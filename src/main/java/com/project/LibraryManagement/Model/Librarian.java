package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Librarian")
public class Librarian {
    @Id
    @SequenceGenerator(name = "librarian_generator", allocationSize = 1)
    @GeneratedValue(generator = "librarian_generator")
    Long librarianId;
    @Column(name = "address", length = 250, nullable = false)
    String address;
    @JsonIgnore
    @Column(name = "password", length = 250, nullable = false)
    String password;
    @Column(name = "firstName", length = 250, nullable = false)
    String firstName;

    @Column(name = "lastName", length = 250, nullable = false)
    String lastName;

    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;

    @OneToMany(mappedBy = "librarian", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Member> members;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "librarians_roles",
            joinColumns = @JoinColumn(name = "librarian_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Collection<Role> roles;

}
