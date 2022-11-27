package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonIgnore
    private Issues issues;
}
