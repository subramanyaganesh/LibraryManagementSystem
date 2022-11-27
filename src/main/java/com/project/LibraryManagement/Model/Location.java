package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
@Table(name = "Location")
public class Location implements Serializable {
    @Id
    @SequenceGenerator(name = "roomNumber_generator", allocationSize = 1, initialValue = 100)
    @GeneratedValue(generator = "roomNumber_generator")
    Long roomNumber;
    @Column(name = "shelfNumber", length = 250, nullable = false)
    Long shelfNumber;
    @Column(name = "level", length = 250, nullable = false)
    Long level;

    public Location() {
    }

    @Builder
    public Location(Long shelfNumber, Long level) {
        this.shelfNumber = shelfNumber;
        this.level = level;
    }

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Book> bookSet;


    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TechnicalReport> technicalReportSet;


    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Thesis> thesisSet;


    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Magazine> magazineSet;


    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Journal> journalSet;
}
