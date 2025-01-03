package com.example.blog_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_app.models.Comment;
import com.example.blog_app.models.Post;
import com.example.blog_app.models.UserObject;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
    List<Comment> findByUser(UserObject user);
}
