package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "book")
public class Book implements Serializable {//owning side

    @Id
    @SequenceGenerator(name = "document_generator", allocationSize = 1)
    @GeneratedValue(generator = "document_generator")
    private Long document_id;

    @Column(name = "title", length = 50, nullable = false, unique = true)
    private String title;

    @Column(name = "edition", length = 100, nullable = false)
    private String edition;

    @Column(name = "year", length = 50, nullable = false)
    private Date year;

    @Column(name = "copyNumber", nullable = false,unique = true)
    private Long copyNumber;
    @ToString.Exclude
    @ManyToMany(targetEntity = Author.class,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Author> authorSet = new HashSet<>();

    public void addAuthor(Author author) {
        this.authorSet.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(long AuthorId) {
        Author author = this.authorSet.stream().filter(t -> t.getAuthorId() == AuthorId).findFirst().orElse(null);
        if (author != null) {
            this.authorSet.remove(author);
            author.getBooks().remove(this);
        }
    }

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Publisher publisher;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Location location;

    public Book() {
    }

    @Builder
    public Book(String title, String edition, Date year, Long copyNumber, Publisher publisher, Location location) {
        this.title = title;
        this.edition = edition;
        this.year = year;
        this.copyNumber = copyNumber;
        this.publisher = publisher;
        this.location = location;
    }

}
