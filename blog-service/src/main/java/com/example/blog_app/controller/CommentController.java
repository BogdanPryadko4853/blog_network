package com.example.blog_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.dto.CommentRequest;
import com.example.blog_app.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/comments/", method = {RequestMethod.GET, RequestMethod.POST,  RequestMethod.HEAD})
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest req) { 
        commentService.save(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/by-post/{postId}/")
    public ResponseEntity<List<CommentRequest>> getAllCommentsForPost(@PathVariable Long postId) { 
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentsForPost(postId));
    }
    @GetMapping(value = "/user/{username}/")
    public ResponseEntity<List<CommentRequest>> getAllCommentsForUser(@PathVariable String username) { 
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentsForUsername(username));
    }


}
