package com.example.blog_app.service;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog_app.dto.VoteRequest;
import com.example.blog_app.models.Post;
import com.example.blog_app.models.Vote;
import com.example.blog_app.models.VoteType;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {
    private final PostRepository postRepository;
    private final AuthService authService;
    private final VoteRepository voteRepository;

    @Transactional
    public void vote(VoteRequest req) {
        // Get post 
        Post post = postRepository
            .findById(req.getPostId())
            .orElseThrow(() -> new IllegalStateException("Post Not Found!"));    
        // 
        Optional<Vote> votesUnderPost = voteRepository
            .findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        
        if (votesUnderPost.isPresent() && 
            votesUnderPost
                .get()
                .getVoteType()
                .equals(req.getVoteType())) {
            throw new IllegalStateException("You have already voted for this post!" + req.getPostId());
        }
        if (VoteType.UPVOTE.equals(req.getVoteType())) 
            post.setVoteCount(post.getVoteCount() + 1);
        else 
            post.setVoteCount(post.getVoteCount() - 1);
        
        voteRepository.save(mapToDto(req, post));
        postRepository.save(post);

    }

    /**
     * Build vote from post and user input
     * @param req
     * @param post
     * @return
     */
    private Vote mapToDto(VoteRequest req, Post post) {
        return Vote.builder()
            .voteType(req.getVoteType())
            .post(post)
            .user(authService.getCurrentUser())
            .build();
    }   
}
