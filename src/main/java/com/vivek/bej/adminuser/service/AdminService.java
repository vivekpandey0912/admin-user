package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.exception.UserNotFoundException;


public interface AdminService {


    public Admin addBook(String emailId, Author author, Genre genre, Book book) throws UserNotFoundException;

}
