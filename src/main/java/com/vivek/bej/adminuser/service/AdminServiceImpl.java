package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Admin;
import com.vivek.bej.adminuser.domain.Author;
import com.vivek.bej.adminuser.domain.Book;
import com.vivek.bej.adminuser.domain.Genre;
import com.vivek.bej.adminuser.repository.AdminRepository;

public class AdminServiceImpl implements AdminService {


    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin addBook(String emailId, Author author, Genre genre, Book book) {
        return null;
    }
}
