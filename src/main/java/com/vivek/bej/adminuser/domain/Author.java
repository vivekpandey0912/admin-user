package com.vivek.bej.adminuser.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Author {

    @Id
    private String id;
    private String name;
    @DBRef
    private List<Genre> genreList;
}
