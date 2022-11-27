package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
public class Publisher {
    @Id
    @SequenceGenerator(name = "Publisher_generator", allocationSize = 1, initialValue = 2100)
    @GeneratedValue(generator = "Publisher_generator")
    Long PublisherId;
    @Column(name = "name", length = 250, nullable = false, unique = true)
    String name;
    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;

    @Builder
    public Publisher(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
    }

    public Publisher() {
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();


    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TechnicalReport> technicalReportSet = new HashSet<>();

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Thesis> thesisSet = new HashSet<>();

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Magazine> magazineSet = new HashSet<>();

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Journal> journalSet = new HashSet<>();
}
