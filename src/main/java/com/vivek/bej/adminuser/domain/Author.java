package com.vivek.bej.adminuser.domain;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document
public class Author {

@Id
    private String authorId;
    private String name;

    public Author(String authorId, String name) {
        this.authorId = authorId;
        this.name = name;
    }

    public Author() {
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + authorId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
