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

import com.example.blog_app.dto.PostRequest;
import com.example.blog_app.dto.PostResponse;
import com.example.blog_app.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = {"/api/posts"}, method = {RequestMethod.GET, RequestMethod.POST})
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * Write to the database
     * @param req
     * @return void || return the orignal PostRequest
     */
    @PostMapping
    public ResponseEntity<PostRequest> createPost(@RequestBody PostRequest req) { 
        // Save user inside the database
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.save(req));
    }

    /**
     * 
     * Get post by PostId
     * @param postId
     * @return
     */
    @GetMapping(value = "/{postId}/")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPost(postId));
    }

    /**
     * 
     * Get all posts in the database
     * @return
     */
    @GetMapping(value = "/")
    public ResponseEntity<List<PostResponse>> getAllPost() { 
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getAllPosts());
    }

    /**
     * Get posts in a subredddit
     * @param postId
     * @return
     */
    @GetMapping("/subreddit/{id}/")
    public ResponseEntity<List<PostResponse>> getPostsBySubReddit(Long postId) { 
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostsBySubReddit(postId));
    }

    /**
     * Get all posts by user
     * @param username
     * @return
     */
    @GetMapping("/{username}/")
    public ResponseEntity<List<PostResponse>> getPostByUser(String username) { 
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostsByUser(username));
    }

}
