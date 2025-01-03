package com.example.blog_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_app.models.UserObject;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Long> {

    Optional<UserObject> findByUsername(String username);

    
}
