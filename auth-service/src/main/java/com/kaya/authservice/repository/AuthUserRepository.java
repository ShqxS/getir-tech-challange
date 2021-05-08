package com.kaya.authservice.repository;

import com.kaya.authservice.entity.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {
  Optional<AuthUser> findByUsername(String username);
}
