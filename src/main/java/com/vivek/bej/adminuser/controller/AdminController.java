package com.vivek.bej.adminuser.controller;


import com.vivek.bej.adminuser.controller.requests.AddBookRequest;
import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.exception.BookAlreadyExist;
import com.vivek.bej.adminuser.exception.UserAlreadyExist;
import com.vivek.bej.adminuser.exception.UserNotFoundException;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/adminbook")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/admin/addBooks")
    public ResponseEntity<?> addBookS(HttpServletRequest httpServletRequest, @RequestBody AddBookRequest addBookRequest) {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            List<Book> bookList = adminService.addBook(emailId, addBookRequest.getBook(),addBookRequest.getAuthor(),addBookRequest.getGenre()).getListOfBooks();
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (UserNotFoundException userNotFound) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        } catch (BookAlreadyExist bookAlreadyExist) {
            return new ResponseEntity<>(bookAlreadyExist.getMessage(), HttpStatus.CONFLICT);
        }

    }



    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin)
    {
        try {
            return new ResponseEntity<>(adminService.registerAdmin(admin),HttpStatus.OK);
        } catch (UserAlreadyExist e) {
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }
    }


}

