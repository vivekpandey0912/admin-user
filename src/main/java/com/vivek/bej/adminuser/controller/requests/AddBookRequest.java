package com.vivek.bej.adminuser.controller.requests;

import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;

import java.util.Objects;

public class AddBookRequest {
    private Book book;
    private Author author;
    private Genre genre;

    // Getters and setters for book, author, and genre
    // ...

    public AddBookRequest(Book book, Author author, Genre genre) {
        this.book = book;
        this.author = author;
        this.genre = genre;
    }

    public AddBookRequest() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
        AddBookRequest that = (AddBookRequest) o;
        return Objects.equals(book, that.book) && Objects.equals(author, that.author) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, author, genre);
    }

    @Override
    public String toString() {
        return "AddBookRequest{" +
                "book=" + book +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
