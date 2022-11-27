package com.project.LibraryManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LibraryManagementApplication {
    //spring.jpa.hibernate.ddl-auto=create-drop
    //if we want to create a new table every time and drop it after sever stops

    //spring.jpa.hibernate.ddl-auto=create
    //if we want to create a new table every time

    //spring.jpa.hibernate.ddl-auto=update
    //if we want to update the existing table

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}
