package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.exception.BookAlreadyExist;
import com.vivek.bej.adminuser.exception.BookNotFoundException;
import com.vivek.bej.adminuser.exception.UserAlreadyExist;
import com.vivek.bej.adminuser.exception.UserNotFoundException;
import com.vivek.bej.adminuser.mailservice.MailService;
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

    private final MailService mailService;

    public AdminServiceImpl(AdminRepository adminRepository, AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, Proxy proxy, MailService mailService) {
        this.adminRepository = adminRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.proxy = proxy;
        this.mailService = mailService;
    }


    @Override
    public Admin registerAdmin(Admin admin) throws UserAlreadyExist {
        if (adminRepository.findById(admin.getEmailId()).isPresent()) {
            throw new UserAlreadyExist("User Already Exists in the database");
        } else {
            admin.setAdminType("admin");
            Admin instanceUser = adminRepository.save(admin);
            if (!instanceUser.getName().isEmpty()) proxy.registerUser(instanceUser);
            mailService.sendRegisterMail(instanceUser.getEmailId(),instanceUser);
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
        mailService.sendBookAddedNotification(emailId,book);
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
                bookToUpdate.setISBN(updatedBookData.getISBN());
                bookToUpdate.setYearOfPublication(updatedBookData.getYearOfPublication());
                bookToUpdate.setBookImage(updatedBookData.getBookImage());

            }

            // Update the author details if provided
            if (updatedAuthorData != null) {
                // If the author already exists in the database (has an ID), update its properties
                if (updatedAuthorData.getId() != null) {
                    Author existingAuthor = authorRepository.findById(updatedAuthorData.getId()).orElse(null);
                    if (existingAuthor != null) {
                        existingAuthor.setName(updatedAuthorData.getName());
                        // Update other author properties as needed...
                        bookToUpdate.setAuthor(existingAuthor);
                    }
                } else {
                    // If the author is new and not in the database, save it first
                    Author newAuthor = authorRepository.save(updatedAuthorData);
                    bookToUpdate.setAuthor(newAuthor);
                }
            }

            // Update the genre details if provided
            if (updatedGenreData != null) {
                // If the genre already exists in the database (has an ID), update its properties
                if (updatedGenreData.getId() != null) {
                    Genre existingGenre = genreRepository.findById(updatedGenreData.getId()).orElse(null);
                    if (existingGenre != null) {
                        existingGenre.setName(updatedGenreData.getName());
                        // Update other genre properties as needed...
                        bookToUpdate.setGenre(existingGenre);
                    }
                } else {
                    // If the genre is new and not in the database, save it first
                    Genre newGenre = genreRepository.save(updatedGenreData);
                    bookToUpdate.setGenre(newGenre);
                }
            }

            // Save the updated book
            adminRepository.save(admin);
            return bookToUpdate;
        } else {
            throw new BookNotFoundException("Book Not Found");
        }
    }

    @Override
    public List<Book> getAllBooks(String emailId) throws UserNotFoundException, BookNotFoundException {
        Admin admin = adminRepository.findById(emailId).orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));
        List<Book> listOfBooks = admin.getListOfBooks();
        if (listOfBooks == null) {
            return Collections.emptyList();
        }
        return listOfBooks;
    }

    // AdminService.java
   @Override
    public void deleteBookByTitle(String emailId, String title) throws UserNotFoundException, BookNotFoundException {
       Admin admin = adminRepository.findById(emailId).orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));

       List<Book> listOfBooks = admin.getListOfBooks();
       if (listOfBooks == null) {
           listOfBooks = new ArrayList<>(); // Create a new empty list if listOfBooks is null
       }

       // Find the book with the given title
       Optional<Book> optionalBook = listOfBooks.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();

       if (optionalBook.isPresent()) {
           Book bookToDelete = optionalBook.get();
           listOfBooks.remove(bookToDelete);
           mailService.sendBookDeletedNotification(emailId,bookToDelete);
           admin.setListOfBooks(listOfBooks);
           adminRepository.save(admin);
       } else {
           throw new BookNotFoundException("Book Not Found");
       }

   }
}



