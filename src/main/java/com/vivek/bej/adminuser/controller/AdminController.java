package com.vivek.bej.adminuser.controller;


import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.exception.UserNotFoundException;
import com.vivek.bej.adminuser.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }



    @PostMapping("/addbook")
    public ResponseEntity<?> addBookS(HttpServletRequest httpServletRequest, @RequestBody Book book,
                                      @RequestBody Author author, @RequestBody Genre genre)
    {
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            return new ResponseEntity<>(adminService.addBooks(emailId,book,author,genre), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Not ", HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin)
    {
        return new ResponseEntity<>(adminService.registerAdmin(admin),HttpStatus.OK);
    }










}
