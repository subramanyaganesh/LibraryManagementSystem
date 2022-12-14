package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Member")
public class Member {
    @Id
    @SequenceGenerator(name = "librarian_generator", allocationSize = 1)
    @GeneratedValue(generator = "librarian_generator")
    Long memberId;
    String address;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    @Column(name = "password", length = 250, nullable = false)
    String password;
    @Column(name = "firstName", length = 250, nullable = false)
    String firstName;
    @Column(name = "lastName", length = 250)
    String lastName;
    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "librarian_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Librarian librarian;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Collection<Role> roles;


}
