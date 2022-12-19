package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Model.Role;
import com.project.LibraryManagement.Repository.MemberRepository;
import com.project.LibraryManagement.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private LibrarianService librarianService;

    public List<Member> getAllMembers() {
        return repository.findAll();
    }


    public Optional<Member> getMemberByEmail(String email) {
        return repository.findByEmail(email);
    }

    public ResponseEntity<String> registerNewMember(Member member) {
        try {
            Optional<Librarian> librarian;
            Optional<Member> memberOptional =
                    repository.findByEmail(member.getEmailId());
            if (memberOptional.isPresent()) {
                throw new IllegalStateException("The member is already existing");
            }
            if ((librarian = librarianService.getLibrarianByEmail(member.getLibrarian().getEmailId())).isPresent())
                member.setLibrarian(librarian.get());
            else throw new Exception("The Mentioned Librarian does not exist");

            Member result = repository.save(member);
            return ResponseHandler.generateResponse("Successfully added Member!", HttpStatus.CREATED, result, "Member");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Member");
        }
    }
    public ResponseEntity<String> updateMember(Member member) {
        try {
            Optional<Librarian> librarian;
            Member member1 = repository.findByEmail(member.getEmailId()).orElseThrow(()->new Exception("Member not Found"));
            if ((librarian = librarianService.getLibrarianByEmail(member.getLibrarian().getEmailId())).isPresent())
                member1.setLibrarian(librarian.get());
            else throw new Exception("The Mentioned Librarian does not exist");

            member1.setAddress(member.getAddress());
            member1.setPassword(member.getPassword());
            member1.setFirstName(member.getFirstName());
            member1.setLastName(member.getLastName());
            member1.setRoles(member.getRoles());
            Member result = repository.save(member1);
            return ResponseHandler.generateResponse("Successfully updated Member!", HttpStatus.CREATED, result, "Member");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Member");
        }
    }

    public ResponseEntity<String> registerNewMemberInBulk(List<Member> memberList) {
        for (Member member : memberList) {
            Optional<Member> memberOptional =
                    repository.findByEmail(member.getEmailId());
            if (memberOptional.isPresent()) {
                throw new IllegalStateException("The member is already existing");
            }
            repository.save(member);

        }
        return new ResponseEntity<>("Successfully added All Member", HttpStatus.OK);

    }

    public ResponseEntity<String> deleteMember(Long member_id) {
        try {
            if (!repository.existsById(member_id)) {
                throw new IllegalStateException("This member id " + member_id + " Does not exist");
            }
            repository.deleteById(member_id);
            return ResponseHandler.generateResponse("Successfully Deleted Member!", HttpStatus.OK, "Success!!", "Member");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Member.class.getSimpleName());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
