package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class JournalArticle {

    @Id
    @SequenceGenerator(name = "document_generator", allocationSize = 1)
    @GeneratedValue(generator = "document_generator")
    private Long document_id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;


    public JournalArticle() {
    }

    @Builder
    public JournalArticle(String title, Journal journal) {
        this.title = title;
        this.journal = journal;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "author_Journal",
            joinColumns = {@JoinColumn(name = "journal_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Set<Author> authorSet = new HashSet<>();

    public void addAuthor(Author author) {
        this.authorSet.add(author);
        author.getJournalArticles().add(this);
    }

    public void removeAuthor(long AuthorId) {
        Author author = this.authorSet.stream().filter(t -> t.getAuthorId() == AuthorId).findFirst().orElse(null);
        if (author != null) {
            this.authorSet.remove(author);
            author.getJournalArticles().remove(this);
        }
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journal_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Journal journal;


}

