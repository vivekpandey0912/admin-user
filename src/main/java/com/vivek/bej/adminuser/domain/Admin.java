package com.vivek.bej.adminuser.domain;
import org.springframework.data.mongodb.core.mapping.*;


import java.beans.Transient;
import java.util.List;
import java.util.Objects;


@Document
public class Admin {

    @MongoId
    private String emailId;

    @Transient
    private String password;

    private String name;
    private String phNo;
    private String profileImg;

    private String adminType;

    List<Book> listOfBooks;

    public Admin(String emailId, String password, String name, String phNo, String profileImg, String adminType, List<Book> listOfBooks) {
        this.emailId = emailId;
        this.password = password;
        this.name = name;
        this.phNo = phNo;
        this.profileImg = profileImg;
        this.adminType = adminType;
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

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phNo='" + phNo + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", adminType='" + adminType + '\'' +
                ", listOfBooks=" + listOfBooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(emailId, admin.emailId) && Objects.equals(password, admin.password) && Objects.equals(name, admin.name) && Objects.equals(phNo, admin.phNo) && Objects.equals(profileImg, admin.profileImg) && Objects.equals(adminType, admin.adminType) && Objects.equals(listOfBooks, admin.listOfBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password, name, phNo, profileImg, adminType, listOfBooks);
    }
}

