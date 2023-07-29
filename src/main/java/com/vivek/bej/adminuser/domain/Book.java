package com.vivek.bej.adminuser.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;

public class Book {


    private String id;
    private String title;
    private String ISBN;
    private String yearOfPublication;
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;


    public Book(String id, String title, String ISBN, String yearOfPublication, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.yearOfPublication = yearOfPublication;
        this.author = author;
        this.genre = genre;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(ISBN, book.ISBN) && Objects.equals(yearOfPublication, book.yearOfPublication) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", yearOfPublication='" + yearOfPublication + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ISBN, yearOfPublication, author, genre);


    }
}
