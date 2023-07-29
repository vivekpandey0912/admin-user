package com.vivek.bej.adminuser.controller;

import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;

import java.util.Objects;

public class AddBookRequest {
    private String emailId;
    private Author author;
    private Genre genre;
    private Book book;

    public AddBookRequest(String emailId, Author author, Genre genre, Book book) {
        this.emailId = emailId;
        this.author = author;
        this.genre = genre;
        this.book = book;
    }

    public AddBookRequest() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddBookRequest that = (AddBookRequest) o;
        return Objects.equals(emailId, that.emailId) && Objects.equals(author, that.author) && Objects.equals(genre, that.genre) && Objects.equals(book, that.book);
    }


    @Override
    public String toString() {
        return "AddBookRequest{" +
                "emailId='" + emailId + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", book=" + book +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, author, genre, book);
    }

    // Constructors, getters, and setters
}