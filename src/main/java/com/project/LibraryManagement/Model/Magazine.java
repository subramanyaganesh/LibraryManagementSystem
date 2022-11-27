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
public class Magazine {

    @Id
    @SequenceGenerator(name = "document_generator", allocationSize = 1)
    @GeneratedValue(generator = "document_generator")
    private Long document_id;

    @Column(length = 50, nullable = false, unique = true)
    private String magazineName;

    @Column(name = "copyNumber", nullable = false,unique = true)
    private Long copyNumber;

    public Magazine() {
    }

    @Builder
    public Magazine(String magazineName, Long copyNumber, Location location, Publisher publisher) {
        this.magazineName = magazineName;
        this.copyNumber = copyNumber;
        this.location = location;
        this.publisher = publisher;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "Contributor_Magazine",
            joinColumns = {@JoinColumn(name = "magazine_id")},
            inverseJoinColumns = {@JoinColumn(name = "contributor_id")})
    @JsonIgnore
    private Set<Contributor> contributorSet = new HashSet<>();

    public void addContributor(Contributor contributor) {
        this.contributorSet.add(contributor);
        contributor.getMagazines().add(this);
    }

    public void removeContributor(long contributorId) {
        Contributor contributor = this.contributorSet.stream().filter(t -> t.getContributorId() == contributorId).findFirst().orElse(null);
        if (contributor != null) {
            this.contributorSet.remove(contributor);
            contributor.getMagazines().remove(this);
        }
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            })
    @JoinTable(name = "Magazine_issue",
            joinColumns = {@JoinColumn(name = "magazine_id")},
            inverseJoinColumns = {@JoinColumn(name = "issue_id")})
    @JsonIgnore
    private Set<Issues> issuesSet = new HashSet<>();

    public void addIssue(Issues issues) {
        this.issuesSet.add(issues);
        issues.getMagazines().add(this);
    }

    public void removeIssue(long IssueId) {
        Issues issues = this.issuesSet.stream().filter(t -> t.getIssueId() == IssueId).findFirst().orElse(null);
        if (issues != null) {
            this.issuesSet.remove(issues);
            issues.getMagazines().remove(this);
        }
    }

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
