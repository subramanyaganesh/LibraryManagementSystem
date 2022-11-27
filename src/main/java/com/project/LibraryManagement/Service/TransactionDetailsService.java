package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Model.TransactionDetails;
import com.project.LibraryManagement.Repository.MemberRepository;
import com.project.LibraryManagement.Repository.TransactionDetailsRepository;
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
    public MemberRepository memberRepository;

    public List<TransactionDetails> getAllTransactionDetails() {
        return transactionDetailsRepository.findAll();
    }
    public Optional<TransactionDetails> getTransactionDetailsById(Long id) {
        return transactionDetailsRepository.findById(id);
    }
    public TransactionDetails getTransactionDetailsByMemberEmail(String email) {
        Member member= memberRepository.findByEmail(email).orElse(null);
        if(member!=null){
           return transactionDetailsRepository.findBymember(member)
                    .orElseThrow(() -> new IllegalStateException(String.format("TransactionDetails not found associated with "+ email)));

        }
        return null;
    }
    public ResponseEntity<String> createTransactionDetails(TransactionDetails transactionDetails) {
        transactionDetailsRepository.save(transactionDetails);
        return new ResponseEntity<>("Successfully added TransactionDetails", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTransactionDetails(Long id) {
        var transactionDetails = transactionDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("TransactionDetails not found with ID %d", id)));

        transactionDetailsRepository.deleteById(transactionDetails.getTransaction_id());
        return new ResponseEntity<>("Successfully Deleted TransactionDetails", HttpStatus.OK);
    }

}
