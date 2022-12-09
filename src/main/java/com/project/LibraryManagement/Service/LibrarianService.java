package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Model.Role;
import com.project.LibraryManagement.Repository.LibrarianRepository;
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
public class LibrarianService implements UserDetailsService {

    @Autowired
    private LibrarianRepository repository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        var user = repository.findByemailId(emailId).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<Librarian> getAllLibrarians() {
        return repository.findAll();
    }

    public ResponseEntity<String> registerNewLibrarian(Librarian Librarian) {
        Optional<Librarian> LibrarianOptional =
                repository.findByemailId(Librarian.getEmailId());
        if (LibrarianOptional.isPresent()) {
            throw new IllegalStateException("The Librarian is already existing");
        }
        repository.save(Librarian);
        return new ResponseEntity<>("Successfully added Librarian", HttpStatus.OK);
    }
    public ResponseEntity<String> registerNewLibrarianInBulk(List<Librarian> librarianList) {
        for (com.project.LibraryManagement.Model.Librarian Librarian : librarianList) {
            Optional<Librarian> LibrarianOptional =
                    repository.findByemailId(Librarian.getEmailId());
            if (LibrarianOptional.isPresent()) {
                throw new IllegalStateException("The Librarian is already existing");
            }
            repository.save(Librarian);
            continue;
        }
        return new ResponseEntity<>("Successfully added All Librarian", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteLibrarian(Long Librarian_id) {
        if (!repository.existsById(Librarian_id)) {
            throw new IllegalStateException("This Librarian id " + Librarian_id + " Does not exist");
        }
        repository.deleteById(Librarian_id);
        return new ResponseEntity<>("Successfully deleted Librarian", HttpStatus.OK);
    }

}
