package com.project.LibraryManagement.Repository;
import com.project.LibraryManagement.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("from Member where emailId=?1")
    Optional<Member> findByEmail(String email);
}
