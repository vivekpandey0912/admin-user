package com.vivek.bej.adminuser.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Book Already Exist")
public class BookAlreadyExist extends Exception {
    public BookAlreadyExist(String message) {
        super(message);
    }
}
