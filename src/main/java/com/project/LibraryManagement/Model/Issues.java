package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString

@Entity

public class Issues {

    @Id
    @SequenceGenerator(name = "document_generator", allocationSize = 1)
    @GeneratedValue(generator = "document_generator")
    private Long issueId;

    @Column(name = "publishDate", length = 50, nullable = false)
    private Date publishDate;

    public Issues() {
    }

    @Builder
    public Issues(Date publishDate) {
        this.publishDate = publishDate;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "issuesSet") //this is the variable name in the owning side
    private Set<Journal> journals=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "issuesSet") //this is the variable name in the owning side
    private Set<Magazine> magazines=new HashSet<>();

    @OneToMany(mappedBy = "issues", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Editor> editorSet;

}
