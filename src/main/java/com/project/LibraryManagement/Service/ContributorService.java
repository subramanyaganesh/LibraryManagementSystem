package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContributorService {
    @Autowired
    public ContributorRepository contributorRepository;

    public List<Contributor> getAllContributors() {
        return contributorRepository.findAll();
    }
    public Optional<Contributor> getContributorById(Long id) {
        return contributorRepository.findbyid(id);
    }

    public Optional<Contributor> getContributorByEmail(String id) {
        return contributorRepository.findByemailId(id);
    }
    public List<Magazine> getAllMagazinesByContributorId(String id) {
        Contributor contributor;
        long i = -1L;
        try {
            i = Long.parseLong(id);
        } catch (Exception e) {/*do nothing*/}
        if (i != -1) {
            contributor = contributorRepository.findById(i).orElse(null);
        } else {
            contributor = contributorRepository.findByfirstName(id).orElse(null);
        }
        if (contributor != null) {
            return new ArrayList<>(contributor.getMagazines());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

}
