package com.vivek.bej.adminuser.repository;

import com.vivek.bej.adminuser.domain.Book;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {
}
