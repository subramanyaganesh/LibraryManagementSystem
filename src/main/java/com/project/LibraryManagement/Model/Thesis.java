package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@ToString
@Entity
public class Thesis {

    @Id
    @SequenceGenerator(name = "document_generator", allocationSize = 1)
    @GeneratedValue(generator = "document_generator")
    private Long document_id;

    @Column(name = "title", length = 50, nullable = false, unique = true)
    private String title;

    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @Column(name = "year", length = 50, nullable = false)
    private Date year;

    @Column(name = "copyNumber", nullable = false,unique = true)
    private Long copyNumber;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "writerId", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Writer writer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Publisher publisher;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Location location;

    public Thesis() {
    }

    @Builder
    public Thesis(String title, String category, Date year, Long copyNumber, Writer writer, Publisher publisher, Location location) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.copyNumber = copyNumber;
        this.writer = writer;
        this.publisher = publisher;
        this.location = location;
    }
}
