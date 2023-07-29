package com.vivek.bej.adminuser.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GenreSequence {

    @Id
    private String id;
    private long genreId;

    public GenreSequence(String id, long genreId) {
        this.id = id;
        this.genreId = genreId;
    }

    public GenreSequence() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }
}
