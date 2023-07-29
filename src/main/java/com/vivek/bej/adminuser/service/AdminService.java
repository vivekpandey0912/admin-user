package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.exception.UserNotFoundException;


public interface AdminService {


    public Admin addBook(String emailId, Book book) throws UserNotFoundException;


    public Admin registerAdmin(Admin admin);

    public Admin addBooks(String emailId, Book book, Author author, Genre genre) throws UserNotFoundException;

}
