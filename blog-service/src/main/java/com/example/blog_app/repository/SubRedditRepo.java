package com.example.blog_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_app.models.SubReddit;


public interface SubRedditRepo extends JpaRepository<SubReddit, Long> {

    Optional<SubReddit> findByName(String subredditName);
       
}
