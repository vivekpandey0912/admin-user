package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.exception.BookAlreadyExist;
import com.vivek.bej.adminuser.exception.UserNotFoundException;
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

    public AdminServiceImpl(AdminRepository adminRepository, AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository) {
        this.adminRepository = adminRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }





    @Override
    public Admin registerAdmin(Admin admin) {
        admin.setAdminType("admin");
        Admin admin1 = adminRepository.save(admin);
        return admin1;
    }

    @Override
    public Admin addBook(String emailId, Book book) throws BookAlreadyExist, UserNotFoundException {
        Admin admin;
        String taskId = UUID.randomUUID().toString();
        book.setBookId(taskId);
        if (adminRepository.findById(emailId).isPresent()) {
            admin = adminRepository.findById(emailId).get();
            List<Book> listOfBooks = admin.getListOfBooks();
            if (listOfBooks == null) {
                listOfBooks = Collections.singletonList(book);
            } else {
                if (listOfBooks.stream().filter(data -> data.getTitle().equalsIgnoreCase(book.getTitle())).findAny().isPresent()) {
                    throw new BookAlreadyExist("Book Already Exist");
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



