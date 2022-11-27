package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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
    @JsonIgnore
    private Librarian librarian;

    /*@OneToOne
    private TransactionDetails transactionDetails;*/


}
