package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.TransactionDetails;
import com.project.LibraryManagement.Service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionDetailsService transactionDetailsService;

    @GetMapping(value = {"/librarian/getTransactionDetails"})
    public List<TransactionDetails> getTransactionDetailsList() {
        return transactionDetailsService.getAllTransactionDetails();
    }

    @GetMapping(value = {"/librarian/getTransactionById/{TransactionId}"})
    public TransactionDetails getBooksByWriterId(@PathVariable("TransactionId") Long id) {
        return transactionDetailsService.getTransactionDetailsById(id).orElse(null);
    }

    @GetMapping(value = {"/librarian/getTransactionByEmail/{email}"})
    public TransactionDetails getJournalsByWriterId(@PathVariable("email") String email) {
        return transactionDetailsService.getTransactionDetailsByMemberEmail(email);
    }

    @PostMapping(value = {"/librarian/addTransaction","/librarian/updateTransaction"})
    public ResponseEntity<String> addTransaction(@RequestBody TransactionDetails transactionDetails) {
        return transactionDetailsService.createTransactionDetails(transactionDetails);
    }

    @DeleteMapping(value = {"/librarian/deleteTransactionDetailsBy/{transactionDetailsId}"})
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionDetailsId) {
        return transactionDetailsService.deleteTransactionDetails(transactionDetailsId);
    }

}
