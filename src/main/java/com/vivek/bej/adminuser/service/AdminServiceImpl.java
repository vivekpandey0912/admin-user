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
        if (adminRepository.findById(admin.getEmailId()).isPresent()) {
            throw new UserAlreadyExist("User Already Exists in the database");
        } else {
            admin.setAdminType("admin");
            Admin instanceUser = adminRepository.save(admin);
            if (!instanceUser.getName().isEmpty()) proxy.registerUser(instanceUser);
            return instanceUser;

        }
    }

    @Override
    public Admin addBook(String emailId, Book book, Author author, Genre genre) throws BookAlreadyExist, UserNotFoundException {
        Admin admin = adminRepository.findById(emailId).orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));

        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        List<Book> listOfBooks = admin.getListOfBooks();
        if (listOfBooks == null) {
            listOfBooks = new ArrayList<>();
            admin.setListOfBooks(listOfBooks);
        }

        if (listOfBooks.stream().anyMatch(data -> data.getTitle().equalsIgnoreCase(book.getTitle()))) {
            throw new BookAlreadyExist("Book Already Exists");
        }

        book.setAuthor(author);
        book.setGenre(genre);
        listOfBooks.add(book);

        return adminRepository.save(admin);

    }


    @Override
    public Book editBook(String emailId, String title, Book updatedBookData, Author updatedAuthorData, Genre updatedGenreData) throws UserNotFoundException, BookNotFoundException {
        Admin admin = adminRepository.findById(emailId).orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));

        List<Book> listOfBooks = admin.getListOfBooks();
        if (listOfBooks == null) {
            throw new BookNotFoundException("Book Not Found");
        }

        // Find the book with the given title
        Optional<Book> optionalBook = listOfBooks.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();

        if (optionalBook.isPresent()) {
            Book bookToUpdate = optionalBook.get();

            // Update the book details if provided
            if (updatedBookData != null) {
                bookToUpdate.setTitle(updatedBookData.getTitle());
                bookToUpdate.setDescription(updatedBookData.getDescription());
                bookToUpdate.setYearOfPublication(updatedBookData.getYearOfPublication());
                bookToUpdate.setIsbn(updatedBookData.getIsbn());
            }

            // Update the author details if provided
            if (updatedAuthorData != null) {
                bookToUpdate.getAuthor().setName(updatedAuthorData.getName());
                bookToUpdate.getAuthor().setEmail(updatedAuthorData.getEmail());
                // Update other author properties as needed...
            }

            // Update the genre details if provided
            if (updatedGenreData != null) {
                bookToUpdate.getGenre().setName(updatedGenreData.getName());
                // Update other genre properties as needed...
            }

            // Save the updated book
            adminRepository.save(admin);
            return bookToUpdate;
        } else {
            throw new BookNotFoundException("Book Not Found");
        }
    }


}



