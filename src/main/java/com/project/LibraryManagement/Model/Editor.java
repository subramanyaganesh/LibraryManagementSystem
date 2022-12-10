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

public class Editor {
    @Id
    @SequenceGenerator(name = "editor_generator", allocationSize = 1, initialValue = 1100)
    @GeneratedValue(generator = "editor_generator")
    Long editorId;
    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;
    @Column(name = "firstName", length = 250, nullable = false)
    String firstName;
    @Column(name = "lastName", length = 250)
    String lastName;

    public Editor() {
    }

    @Builder
    public Editor(String emailId, String firstName, String lastName) {
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
            mappedBy = "editorSet")
    @JsonIgnore
    private Set<Issues> issues = new HashSet<>();
}
