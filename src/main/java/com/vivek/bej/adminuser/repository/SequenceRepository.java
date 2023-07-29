package com.vivek.bej.adminuser.repository;

import com.vivek.bej.adminuser.domain.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String> {
}
