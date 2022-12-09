package com.project.LibraryManagement.Repository;
import com.project.LibraryManagement.Model.Author;
import com.project.LibraryManagement.Model.Book;
import com.project.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("from Member where id=?1")
    Optional<Member> findbyid(Long id);


    @Query("from Member where emailId=?1")
    Optional<Member> findByEmail(String email);

    @Query(value = "insert  into member (address, email_id, first_name, last_name, librarian_id, member_id)  values (?, ?, ?, ?, ?, ?)", nativeQuery = true)
    Optional<Member> saveData(String address, String email_id, String first_name, String last_name, Long librarian_id, Long member_id);

}
