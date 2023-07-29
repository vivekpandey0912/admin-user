package com.vivek.bej.adminuser.mailservice;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    public void sendBookAddedNotification(String emailId, Book book) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailId);
        message.setSubject("New Book Added");
        message.setText("A new book has been added: " + book.getTitle() + book);
        mailSender.send(message);
    }

    @Override
    public void sendBookDeletedNotification(String emailId, Book book) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailId);
        message.setSubject("Book Deleted");
        message.setText("The book has been deleted: " + book.getTitle());
        mailSender.send(message);
    }

    @Override
    public void sendRegisterMail(String emailId, Admin admin) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailId);
        message.setSubject("You got Registered Successfully");
        message.setText("Thanks for registering" + admin.getName());
        mailSender.send(message);

    }
}
