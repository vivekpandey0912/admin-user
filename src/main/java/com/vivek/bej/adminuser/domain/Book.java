package com.vivek.bej.adminuser.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Id;

public class Book {

    @Id
    private String id;
    private String title;
    private String ISBN;
    // Other book-related fields, e.g., publication year, publisher, etc.
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;


}
