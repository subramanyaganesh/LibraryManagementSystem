package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.TransactionDetails;
import com.project.LibraryManagement.Service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionDetailsService transactionDetailsService;

    @GetMapping(value = {"/librarian/getTransactionDetails1"})
    public @ResponseBody List<TransactionDetails> getTransactionDetailsList() {
        return transactionDetailsService.getAllTransactionDetails();
    }

    @GetMapping(value = {"/librarian/getTransactionById1/{TransactionId}"})
    public  @ResponseBody TransactionDetails getBooksByWriterId(@PathVariable("TransactionId") Long id) {
        return transactionDetailsService.getTransactionDetailsById(id).orElse(null);
    }

    @GetMapping(value = {"/librarian/getTransactionByEmail1/{email}"})
    public @ResponseBody  TransactionDetails getJournalsByWriterId(@PathVariable("email") String email) {
        return transactionDetailsService.getTransactionDetailsByMemberEmail(email);
    }

    @PostMapping(value = {"/librarian/addTransaction"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addTransaction(@RequestBody TransactionDetails transactionDetails) {
        return transactionDetailsService.createTransactionDetails(transactionDetails);
    }
    @PutMapping(value = {"/librarian/updateTransaction"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTransaction(@RequestBody TransactionDetails transactionDetails) {
        return transactionDetailsService.updateTransactionDetails(transactionDetails);
    }

    @DeleteMapping(value = {"/librarian/deleteTransactionDetailsBy/{transactionDetailsId}"})
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long transactionDetailsId) {
        return transactionDetailsService.deleteTransactionDetails(transactionDetailsId);
    }

}
