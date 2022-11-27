package com.project.LibraryManagement.Service;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Repository.ContributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContributorService {
    @Autowired
    public ContributorRepository contributorRepository;

    public List<Contributor> getAllContributors() {
        return contributorRepository.findAll();
    }


    public List<Magazine> getAllMagazinesByContributorId(Long id){
        Contributor contributor= contributorRepository.findById(id).orElse(null);
        if (contributor!=null){
            return new ArrayList<>(contributor.getMagazines());
        }
        throw new IllegalStateException("The Writer does not exist");
    }

}
