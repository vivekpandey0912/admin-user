package com.vivek.bej.adminuser.mailservice;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Book;

public interface MailService{


    void sendBookAddedNotification(String emailId, Book book);
    void sendBookDeletedNotification(String emailId, Book book);

    void sendRegisterMail(String emailId, Admin admin);
}
