package com.vivek.bej.adminuser.repository;

import com.vivek.bej.adminuser.domain.GenreSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreSequenceRepository extends MongoRepository<GenreSequence, String> {
}
