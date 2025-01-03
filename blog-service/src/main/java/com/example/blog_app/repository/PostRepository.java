package com.example.blog_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_app.models.Post;
import com.example.blog_app.models.SubReddit;
import com.example.blog_app.models.UserObject;


/*
 * JPA - Java Persistence 
 * - It is used to persist data between Java object and relational 
 *   database
 * - JPA acts as a bridge between object oriented domain models and relational
 *   database systems
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findBySubredditName(SubReddit subredditName);
    List<Post> findByUser(UserObject user);

}
