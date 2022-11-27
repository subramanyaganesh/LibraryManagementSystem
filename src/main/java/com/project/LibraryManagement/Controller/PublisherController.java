package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.*;
import com.project.LibraryManagement.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping(value = {"/getPublishers"})
    public List<Publisher> getPublisherList() {
        return publisherService.getAllPublishers();
    }

    @GetMapping(value = {"/getAllBooksBy/{publisherId}"})
    public List<Book> getBooksByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllBooksByPublisherId(id);
    }

    @GetMapping(value = {"/getAllJournalsBy/{publisherId}"})
    public List<Journal> getJournalsByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllJournalsByPublisherId(id);
    }

    @GetMapping(value = {"/getAllMagazinesBy/{publisherId}"})
    public List<Magazine> getMagazinesByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllMagazinesByPublisherId(id);
    }

    @GetMapping(value = {"/getAllThesisBy/{publisherId}"})
    public List<Thesis> getThesisByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllThesisByPublisherId(id);
    }

    @GetMapping(value = {"/getAllTechnicalReportsBy/{publisherId}"})
    public List<TechnicalReport> getTechnicalReportsByWriterId(@PathVariable("publisherId") Long id) {
        return publisherService.getAllTechnicalReportByPublisherId(id);
    }

}
