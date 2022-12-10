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
    public Issues(Date publishDate, Set<Editor> editorSet) {
        this.publishDate = publishDate;
        this.editorSet = editorSet;
    }


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "issuesSet") //this is the variable name in the owning side
    @JsonIgnore
    private Set<Journal> journals = new HashSet<>();
    public void addJournal(Journal journal) {
        this.journals.add(journal);
        journal.getIssuesSet().add(this);
    }

    public void removeJournal(long journalsId) {
        Journal journals = this.journals.stream().filter(t -> t.getDocument_id() == journalsId).findFirst().orElse(null);
        if (journals != null) {
            this.journals.remove(journals);
            journals.getIssuesSet().remove(this);
        }
    }
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "issuesSet") //this is the variable name in the owning side
    @JsonIgnore
    private Set<Magazine> magazines = new HashSet<>();

    public void addMagazine(Magazine magazine) {
        this.magazines.add(magazine);
        magazine.getIssuesSet().add(this);
    }

    public void removeMagazine(long magazineId) {
        Magazine magazine = this.magazines.stream().filter(t -> t.getDocument_id() == magazineId).findFirst().orElse(null);
        if (magazine != null) {
            this.magazines.remove(magazine);
            magazine.getIssuesSet().remove(this);
        }
    }

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "editor_issues",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
   @JsonIgnore
    private Set<Editor> editorSet = new HashSet<>();

    public void addEditor(Editor editor) {
        this.editorSet.add(editor);
        editor.getIssues().add(this);
    }

    public void removeEditor(long EditorId) {
        Editor editor = this.editorSet.stream().filter(t -> t.getEditorId() == EditorId).findFirst().orElse(null);
        if (editor != null) {
            this.editorSet.remove(editor);
            editor.getIssues().remove(this);
        }
    }

}
