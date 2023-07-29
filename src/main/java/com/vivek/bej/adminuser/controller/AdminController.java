package com.vivek.bej.adminuser.controller;


import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.exception.UserNotFoundException;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Other code...

    @PostMapping("/addBooks")
    public ResponseEntity<?> addBookS(HttpServletRequest httpServletRequest, @RequestBody AddBookRequest addBookRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            Admin admin = adminService.addBooks(emailId, addBookRequest.getBook(), addBookRequest.getAuthor(), addBookRequest.getGenre());
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not found ", HttpStatus.NOT_FOUND);
        }
    }

    // Other code...

    // Define a DTO class to wrap Book, Author, and Genre




    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin)
    {
        return new ResponseEntity<>(adminService.registerAdmin(admin),HttpStatus.OK);
    }

    static class AddBookRequest {
        private Book book;
        private Author author;
        private Genre genre;

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

        // getters and setters for book, author, and genre
        //...
    }

}

