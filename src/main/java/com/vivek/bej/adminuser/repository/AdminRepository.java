package com.vivek.bej.adminuser.repository;

import com.vivek.bej.adminuser.domain.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {
}
