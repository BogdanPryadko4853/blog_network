package com.example.blog_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_app.models.Post;
import com.example.blog_app.models.UserObject;
import com.example.blog_app.models.Vote;

// THIS IS SENSITIVE ie IT ACTS AS A SQL QUERY
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, UserObject currentUser);
}
