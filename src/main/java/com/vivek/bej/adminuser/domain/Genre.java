package com.vivek.bej.adminuser.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Genre {

    @Id
    private String id;
    private String name;
    // Other genre-related fields, e.g., description, category, etc.
    @DBRef
    private List<Book> books;
}
