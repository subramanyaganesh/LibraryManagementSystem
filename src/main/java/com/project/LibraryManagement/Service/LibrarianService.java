package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.Librarian;
import com.project.LibraryManagement.Model.Role;
import com.project.LibraryManagement.Repository.LibrarianRepository;
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
    public Optional<Librarian> getLibrarianByEmail(String email) {
        return repository.findByemailId(email);
    }

    public ResponseEntity<Object> registerNewLibrarian(Librarian librarian) {
        try {
            Optional<Librarian> LibrarianOptional =
                    repository.findByemailId(librarian.getEmailId());
            if (LibrarianOptional.isPresent()) {
                throw new IllegalStateException("The Librarian is already existing");
            }
            Librarian result = repository.save(librarian);
            return ResponseHandler.generateResponse("Successfully added Librarian!", HttpStatus.CREATED, result, "Librarian");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Librarian");
        }
    }
    public ResponseEntity<Object> updateLibrarian(Librarian librarian) {
        try {
            Librarian librarian1 = repository.findByemailId(librarian.getEmailId()).orElseThrow(()->new Exception("Librarian Not Found"));
            librarian1.setAddress(librarian.getAddress());
            librarian1.setFirstName(librarian.getFirstName());
            librarian1.setLastName(librarian.getLastName());
            librarian1.setPassword(librarian.getPassword());
            Librarian result = repository.save(librarian1);
            return ResponseHandler.generateResponse("Successfully updated Librarian!", HttpStatus.CREATED, result, "Librarian");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, "Librarian");
        }
    }

    public ResponseEntity<String> registerNewLibrarianInBulk(List<Librarian> librarianList) {
        for (Librarian Librarian : librarianList) {
            Optional<Librarian> LibrarianOptional =
                    repository.findByemailId(Librarian.getEmailId());
            if (LibrarianOptional.isPresent()) {
                throw new IllegalStateException("The Librarian is already existing");
            }
            repository.save(Librarian);
        }
        return new ResponseEntity<>("Successfully added All Librarian", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteLibrarian(Long Librarian_id) {
        try {
            if (!repository.existsById(Librarian_id)) {
                throw new IllegalStateException("This Librarian id " + Librarian_id + " Does not exist");
            }
            repository.deleteById(Librarian_id);
            return ResponseHandler.generateResponse("Successfully Deleted Librarian!", HttpStatus.OK, "Success!!", "Librarian");
        } catch (Exception e) {
            return ResponseHandler.generateResponse("The exception is " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null, Librarian.class.getSimpleName());
        }
    }

}
