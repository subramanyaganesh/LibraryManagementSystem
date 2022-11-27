package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

    Optional<TransactionDetails> findBymember(Member member);
}
