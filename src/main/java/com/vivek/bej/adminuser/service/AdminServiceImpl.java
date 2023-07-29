package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.exception.BookAlreadyExist;
import com.vivek.bej.adminuser.exception.UserAlreadyExist;
import com.vivek.bej.adminuser.exception.UserNotFoundException;
import com.vivek.bej.adminuser.proxy.Proxy;
import com.vivek.bej.adminuser.repository.AdminRepository;
import com.vivek.bej.adminuser.repository.AuthorRepository;
import com.vivek.bej.adminuser.repository.BookRepository;
import com.vivek.bej.adminuser.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {


    private final AdminRepository adminRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final Proxy proxy;

    public AdminServiceImpl(AdminRepository adminRepository, AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, Proxy proxy) {
        this.adminRepository = adminRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.proxy = proxy;
    }





    @Override
    public Admin registerAdmin(Admin admin) throws UserAlreadyExist {
        if(adminRepository.findById(admin.getEmailId()).isPresent())
        {
            throw new UserAlreadyExist("User Already Exists in the database");
        }
        else
        {
            admin.setAdminType("admin");
            Admin instanceUser = adminRepository.save(admin);
            if (!instanceUser.getName().isEmpty()) proxy.registerUser(instanceUser);
            return instanceUser;

        }
    }

    @Override
    public Admin addBook(String emailId, Book book, Author author, Genre genre) throws BookAlreadyExist, UserNotFoundException {
        Admin admin;
        if (adminRepository.findById(emailId).isPresent()) {
            admin = adminRepository.findById(emailId).get();
            List<Book> listOfBooks = admin.getListOfBooks();
            if (listOfBooks == null) {
                book.setAuthor(author);
                book.setGenre(genre);
                listOfBooks = Collections.singletonList(book);
            } else {
                if (listOfBooks.stream().anyMatch(data -> data.getTitle().equalsIgnoreCase(book.getTitle()))) {
                    throw new BookAlreadyExist("Book Already Exists");
                } else {
                    listOfBooks.add(book);
                }
            }
            admin.setListOfBooks(listOfBooks);
        } else {
            throw new UserNotFoundException("User Not Found Exception");
        }
        return adminRepository.save(admin);
    }


}



