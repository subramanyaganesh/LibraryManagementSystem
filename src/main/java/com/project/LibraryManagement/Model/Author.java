package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Author {// this is non-owning side
    @Id
    @SequenceGenerator(name = "author_generator", allocationSize = 1, initialValue = 100)
    @GeneratedValue(generator = "author_generator")
    Long authorId;

    @Column(name = "emailId", length = 250, nullable = false, unique = true)
    String emailId;

    @Column(name = "firstName", length = 250, nullable = false)
    String firstName;

    @Column(name = "lastName", length = 250)
    String lastName;

    @Builder
    public Author( String emailId, String firstName, String lastName) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {
    }
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "authorSet")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            },
            mappedBy = "authorSet") //this is the variable name in the owning side
    @JsonIgnore
    private Set<JournalArticle> journalArticles=new HashSet<>();

}
