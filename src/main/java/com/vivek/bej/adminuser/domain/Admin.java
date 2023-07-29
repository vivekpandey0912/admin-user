package com.vivek.bej.adminuser.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;


import java.util.List;
import java.util.Objects;


@Document
public class Admin {

    @Id
    private String emailId;

    private String password;
    private String name;


    @DBRef
    List<Book> listOfBooks;

    public Admin(String emailId, String password, String name, List<Book> listOfBooks) {
        this.emailId = emailId;
        this.password = password;
        this.name = name;
        this.listOfBooks = listOfBooks;
    }

    public Admin() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(emailId, admin.emailId) && Objects.equals(password, admin.password) && Objects.equals(name, admin.name) && Objects.equals(listOfBooks, admin.listOfBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, name, listOfBooks);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", listOfBooks=" + listOfBooks +
                '}';
    }
}

