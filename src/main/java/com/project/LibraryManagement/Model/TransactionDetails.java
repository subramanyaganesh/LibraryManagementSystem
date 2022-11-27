package com.project.LibraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
public class TransactionDetails {

    @Id
    @SequenceGenerator(name = "transaction_generator", allocationSize = 1)
    @GeneratedValue(generator = "transaction_generator")
    private Long transaction_id;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public TransactionDetails() {
    }
    @Builder
    public TransactionDetails(LocalDate borrowDate, LocalDate returnDate, Member member, Book bookSet, Journal journalSet, Magazine magazineSet, TechnicalReport technicalReportSet, Thesis thesisSet) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.member = member;
        this.bookSet = bookSet;
        this.journalSet = journalSet;
        this.magazineSet = magazineSet;
        this.technicalReportSet = technicalReportSet;
        this.thesisSet = thesisSet;
    }




    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Member member;


    @OneToOne
    @JoinColumn(name = "book_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Book bookSet;


    @OneToOne
    @JoinColumn(name = "journal_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Journal journalSet;


    @OneToOne
    @JoinColumn(name = "magazine_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Magazine magazineSet;


    @OneToOne
    @JoinColumn(name = "technicalReport_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private TechnicalReport technicalReportSet;


    @OneToOne
    @JoinColumn(name = "thesis_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Thesis thesisSet;

}
