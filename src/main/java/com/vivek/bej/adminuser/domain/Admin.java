package com.vivek.bej.adminuser.domain;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class Admin {
private String emailId;
private String name;
private List<Book> listBook;

    public Admin(String emailId, String name, List<Book> listBook) {
        this.emailId = emailId;
        this.name = name;
        this.listBook = listBook;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(emailId, admin.emailId) && Objects.equals(name, admin.name) && Objects.equals(listBook, admin.listBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, name, listBook);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", listBook=" + listBook +
                '}';
    }
}
