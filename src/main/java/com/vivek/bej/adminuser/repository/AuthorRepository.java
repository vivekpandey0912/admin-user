package com.vivek.bej.adminuser.repository;

import com.vivek.bej.adminuser.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author,String> {
    Optional<Author> findByName(String name);

}
