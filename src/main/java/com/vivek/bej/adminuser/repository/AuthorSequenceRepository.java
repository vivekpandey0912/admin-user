package com.vivek.bej.adminuser.repository;

import com.vivek.bej.adminuser.domain.AuthorSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorSequenceRepository extends MongoRepository <AuthorSequence, String> {

        }
