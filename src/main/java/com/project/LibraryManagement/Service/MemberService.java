package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Member;

import com.project.LibraryManagement.Model.Role;
import com.project.LibraryManagement.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
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

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public ResponseEntity<String> registerNewMember(Member member) {
        Optional<Member> memberOptional =
                repository.findByEmail(member.getEmailId());
        if (memberOptional.isPresent()) {
            throw new IllegalStateException("The member is already existing");
        }
        repository.save(member);
        return new ResponseEntity<>("Successfully added Member", HttpStatus.OK);
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
        if (!repository.existsById(member_id)) {
            throw new IllegalStateException("This member id " + member_id + " Does not exist");
        }
        repository.deleteById(member_id);
        return new ResponseEntity<>("Successfully deleted Member", HttpStatus.OK);
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
