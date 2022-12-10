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
    private Set<Journal> journals=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "issuesSet") //this is the variable name in the owning side
    private Set<Magazine> magazines=new HashSet<>();


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
    private Set<Editor> editorSet=new HashSet<>();

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


   /* @OneToMany(mappedBy = "issues", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)

    @JsonIgnore
    private Set<Editor> editorSet;*/

}
