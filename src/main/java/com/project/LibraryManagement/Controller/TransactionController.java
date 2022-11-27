package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.TransactionDetails;
import com.project.LibraryManagement.Service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionDetailsService transactionDetailsService;

    @GetMapping(value = {"/getTransactionDetails"})
    public List<TransactionDetails> getTransactionDetailsList() {
        return transactionDetailsService.getAllTransactionDetails();
    }

    @GetMapping(value = {"/getTransactionById/{TransactionId}"})
    public TransactionDetails getBooksByWriterId(@PathVariable("TransactionId") Long id) {
        return transactionDetailsService.getTransactionDetailsById(id).orElse(null);
    }

    @GetMapping(value = {"/getTransactionByEmail/{email}"})
    public TransactionDetails getJournalsByWriterId(@PathVariable("email") String email) {
        return transactionDetailsService.getTransactionDetailsByMemberEmail(email);
    }

}
