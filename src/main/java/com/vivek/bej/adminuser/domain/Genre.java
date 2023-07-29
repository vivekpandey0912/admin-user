package com.vivek.bej.adminuser.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document
public class Genre {

    @Id
    private String genreId;
    private String name;
    // Other genre-related fields, e.g., description, category, etc.


    public Genre() {
    }

    public Genre(String GenreId, String name) {
        this.genreId = GenreId;
        this.name = name;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genreId, genre.genreId) && Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + genreId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
