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
public class Journal {

    @Id
    @SequenceGenerator(name = "document_generator", allocationSize = 1)
    @GeneratedValue(generator = "document_generator")
    private Long document_id;

    @Column(name = "journalId", length = 50, nullable = false)
    private String journalId;

    @Column(name = "journalName", length = 50, nullable = false)
    private String journalName;

    @Column(name = "copyNumber", nullable = false,unique = true)
    private Long copyNumber;

    public Journal() {
    }
@Builder
    public Journal(String journalId, String journalName, Long copyNumber, Publisher publisher, Location location) {
        this.journalId = journalId;
        this.journalName = journalName;
        this.copyNumber = copyNumber;
        this.publisher = publisher;
        this.location = location;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "issues_journal",
            joinColumns = {@JoinColumn(name = "issue_id")},
            inverseJoinColumns = {@JoinColumn(name = "journal_id")})
    @JsonIgnore
    private Set<Issues> issuesSet=new HashSet<>();

    public void addIssue(Issues issues) {
        this.issuesSet.add(issues);
        issues.getJournals().add(this);
    }

    public void removeIssue(long IssueId) {
        Issues issues = this.issuesSet.stream().filter(t -> t.getIssueId() == IssueId).findFirst().orElse(null);
        if (issues != null) {
            this.issuesSet.remove(issues);
            issues.getJournals().remove(this);
        }
    }
    @OneToMany(mappedBy = "journal", fetch = FetchType.LAZY,
            //this is the variable name of Librarian Object created in Member class
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<JournalArticle> journalArticles;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonIgnore
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
//this name here specifies how the pk for the one side has to be named
    @JsonIgnore
    private Location location;

}

