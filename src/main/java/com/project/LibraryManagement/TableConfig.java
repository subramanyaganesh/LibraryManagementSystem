package com.project.LibraryManagement;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.*;
import com.project.LibraryManagement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
public class TableConfig {
    @Autowired
    private BookService bookService;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private TechnicalReportService technicalReportService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private JournalArticleService journalArticleService;
    @Autowired
    private MagazineService magazineService;
    @Autowired
    private WriterService writerService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private TransactionDetailsService transactionDetailsService;
/*    @Autowired
    private BCryptPasswordEncoder passwordEncoder;*/

    @Bean
    CommandLineRunner DefaultCreator() {
        return args -> {
            Location location1 = Location.builder()
                    .level(1L)
                    .shelfNumber(8L)
                    .build();
            locationService.createLocation(location1);


            Author author1 = Author.builder()
                    .firstName("author1firstname")
                    .lastName("author1lastname")
                    .emailId("author1@gmail.com").build();
            Writer writer1 = Writer.builder()
                    .firstName("writer1firstname")
                    .lastName("writer1lastname")
                    .emailId("writer1@gmail.com").build();


            Publisher publisher1 = Publisher.builder()
                    .name("Publisher1")
                    .emailId("publisher1@gmail.com").build();


            Book book1 = Book.builder()
                    .title("Book1")
                    .edition("9th edition")
                    .year(new Date(345678))
                    .location(location1)
                    .publisher(publisher1)
                    .copyNumber(5L)
                    .build();
            book1.addAuthor(author1);
            bookService.createBook(book1);
            Book book2 = Book.builder()
                    .title("Book2")
                    .edition("12th edition")
                    .year(new Date(9876234))
                    .location(location1)
                    .publisher(publisher1)
                    .copyNumber(7L)
                    .build();
            book1.addAuthor(author1);
            bookService.createBook(book2);

            Thesis thesis1 = Thesis.builder()
                    .title("thesis1")
                    .year(new Date(345678))
                    .location(location1)
                    .copyNumber(5L)
                    .writer(writer1)
                    .publisher(publisher1)
                    .category("Math")
                    .build();
            thesisService.createThesis(thesis1);

            TechnicalReport technicalReport2 = TechnicalReport.builder()
                    .title("technicalReport2")
                    .year(new Date(9876561))
                    .category("Geometry")
                    .copyNumber(10L)
                    .writer(writer1)
                    .location(location1)
                    .publisher(publisher1)
                    .build();
            technicalReportService.createTechnicalReport(technicalReport2);

            Magazine magazine3 = Magazine.builder()
                    .magazineName("Magazine3")
                    .location(location1)
                    .publisher(publisher1)
                    .copyNumber(5L)
                    .build();
            magazine3.addIssue(Issues.builder().publishDate(new Date(1234556)).build());
            magazine3.addContributor(Contributor.builder().lastName("contributor1lastName").firstName("contributor1firstName").emailId("contributor1@gmail.com")
                    .build());
            magazineService.createMagazine(magazine3);


            Journal journal1 = Journal.builder()
                    .copyNumber(5L)
                    .location(location1)
                    .publisher(publisher1)
                    .journalId("A12")
                    .journalName("journal1Name")
                    .build();
            journal1.addIssue(Issues.builder().publishDate(new Date(9874543)).build());
            journalService.createJournal(journal1);

            JournalArticle journalArticle1 = JournalArticle.builder()
                    .journal(journal1)
                    .title("journalArticle1Title")
                    .build();
            journalArticleService.createJournalArticle(journalArticle1);


            Librarian librarian1 = Librarian.builder()
                    .address("32 S state st")
                    .emailId("john@gmail.com")
                    .firstName("librarian1firstName")
                    .lastName("librarian1lastName")
                    .build();
            Librarian librarian2 = Librarian.builder()
                    .address("2051 new Avn")
                    .emailId("ram@gmail.com")
                    .firstName("librarian2lastName")
                    .lastName("librarian2lastName")
                    .build();
            librarianService.registerNewLibrarianInBulk(List.of(librarian1, librarian2));
            Member member1 = Member.builder()
                    .address("32 S state st")
                    .firstName("member1firstName")
                    .lastName("member1lastName")
                    .emailId("alisha@gmail.com")
                    .password("adsfkhc")
                    .librarian(librarian1)
                    .build();
            Member member2 = Member.builder()
                    .address("2051 new Avn")
                    .firstName("member2firstName")
                    .lastName("member2lastName")
                    .password("aiuysdgfv")
                    .emailId("kiran@gmail.com")
                    .librarian(librarian1)
                    .build();
            memberService.registerNewMemberInBulk(List.of(member2, member1));

            TransactionDetails transactionDetails= TransactionDetails.builder()
                    .returnDate(LocalDate.now())
                    .borrowDate(LocalDate.now().plusDays(3))
                    .bookSet(book1)
                    .member(member1)
                    .build();

            transactionDetailsService.createTransactionDetails(transactionDetails);

        };
    }
}