package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.MemberRepository;
import com.project.LibraryManagement.Repository.TransactionDetailsRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionDetailsService {
    @Autowired
    public TransactionDetailsRepository transactionDetailsRepository;
    @Autowired
    public BookService bookService;
    @Autowired
    public MagazineService magazineService;
    @Autowired
    public JournalService journalService;
    @Autowired
    public ThesisService thesisService;
    @Autowired
    public TechnicalReportService technicalReportService;
    @Autowired
    public MemberService memberService;


    @Autowired
    public MemberRepository memberRepository;

    public List<TransactionDetails> getAllTransactionDetails() {
        return transactionDetailsRepository.findAll();
    }

    public Optional<TransactionDetails> getTransactionDetailsById(Long id) {
        return transactionDetailsRepository.findById(id);
    }

    public TransactionDetails getTransactionDetailsByMemberEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        if (member != null) {
            return transactionDetailsRepository.findBymember(member)
                    .orElseThrow(() -> new IllegalStateException(String.format("TransactionDetails not found associated with " + email)));

        }
        return null;
    }

    public ResponseEntity<String> createTransactionDetails(TransactionDetails transactionDetails) {
        try {
            Optional<Member> member;
            List<Book> book;
            List<Journal> journal;
            List<Magazine> magazine;
            List<TechnicalReport> technicalReport;
            List<Thesis> thesis;


            if ((member = memberService.getMemberByEmail(transactionDetails.getMember().getEmailId())).isPresent())
                transactionDetails.setMember(member.get());

            if (transactionDetailsRepository.findBymember(transactionDetails.getMember()).isPresent()) {
                throw new Exception("This Member already has a transaction");
            }

            if (!(book = bookService.getSpecificBook(transactionDetails.getBookSet().getDocument_id().toString())).isEmpty())
                transactionDetails.setBookSet(book.get(0));

            if (!(journal = journalService.getSpecificJournal(transactionDetails.getJournalSet().getDocument_id().toString())).isEmpty())
                transactionDetails.setJournalSet(journal.get(0));

            if (!(magazine = magazineService.getSpecificMagazine(transactionDetails.getMagazineSet().getDocument_id().toString())).isEmpty())
                transactionDetails.setMagazineSet(magazine.get(0));

            if (!(technicalReport = technicalReportService.getSpecificTechnicalReport(transactionDetails.getTechnicalReportSet().getDocument_id().toString())).isEmpty())
                transactionDetails.setTechnicalReportSet(technicalReport.get(0));

            if (!(thesis = thesisService.getSpecificThesis(transactionDetails.getThesisSet().getDocument_id().toString())).isEmpty())
                transactionDetails.setThesisSet(thesis.get(0));

            TransactionDetails result = transactionDetailsRepository.save(transactionDetails);
            return ResponseHandler.generateResponse("Successfully added transaction!", HttpStatus.CREATED, result, "TransactionDetails");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "TransactionDetails");
        }
    }

    public ResponseEntity<String> updateTransactionDetails(TransactionDetails transactionDetails) {
        try {

            Optional<Member> member;
            List<Book> book;
            List<Journal> journal;
            List<Magazine> magazine;
            List<TechnicalReport> technicalReport;
            List<Thesis> thesis;

            member = memberService.getMemberByEmail(transactionDetails.getMember().getEmailId());
            if (member.isPresent()) {
                TransactionDetails specificBook = transactionDetailsRepository.findBymember(member.get()).orElseThrow(Exception::new);
                specificBook.setMember(member.get());

                if (!(book = bookService.getSpecificBook(transactionDetails.getBookSet().getDocument_id().toString())).isEmpty())
                    specificBook.setBookSet(book.get(0));

                if (!(journal = journalService.getSpecificJournal(transactionDetails.getJournalSet().getDocument_id().toString())).isEmpty())
                    specificBook.setJournalSet(journal.get(0));

                if (!(magazine = magazineService.getSpecificMagazine(transactionDetails.getMagazineSet().getDocument_id().toString())).isEmpty())
                    specificBook.setMagazineSet(magazine.get(0));

                if (!(technicalReport = technicalReportService.getSpecificTechnicalReport(transactionDetails.getTechnicalReportSet().getDocument_id().toString())).isEmpty())
                    specificBook.setTechnicalReportSet(technicalReport.get(0));

                if (!(thesis = thesisService.getSpecificThesis(transactionDetails.getThesisSet().getDocument_id().toString())).isEmpty())
                    specificBook.setThesisSet(thesis.get(0));

                TransactionDetails result = transactionDetailsRepository.save(specificBook);
                return ResponseHandler.generateResponse("Successfully added transaction!", HttpStatus.CREATED, result, "TransactionDetails");
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "TransactionDetails");
        }
        return null;
    }

    public ResponseEntity<String> deleteTransactionDetails(Long id) {
        try {
            var transactionDetails = transactionDetailsRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException(String.format("TransactionDetails not found with ID %d", id)));

            transactionDetailsRepository.deleteById(transactionDetails.getTransaction_id());
            return ResponseHandler.generateResponse("Successfully Deleted TransactionDetails!", HttpStatus.OK, "Success!!", "TransactionDetails");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, TransactionDetails.class.getSimpleName());
        }
    }

}
