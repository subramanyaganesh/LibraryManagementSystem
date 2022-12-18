package com.project.LibraryManagement.Repository;

import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    @Query("from TransactionDetails where id=?1")
    Optional<TransactionDetails> findbyid(Long id);

    Optional<TransactionDetails> findBymember(Member member);

    @Query(value = "insert  into transaction_details (book_id, borrow_date, journal_id, magazine_id, member_id, return_date, technical_report_id, thesis_id, transaction_id)  values (?, ?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<TransactionDetails> saveData(Long book_id, Date borrow_date, Long journal_id,Long magazine_id,Long member_id,Date return_date,Long technical_report_id,Long thesis_id,Long transaction_id);

}
