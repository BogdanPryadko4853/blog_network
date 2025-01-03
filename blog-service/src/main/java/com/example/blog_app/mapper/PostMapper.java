package com.example.blog_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.blog_app.dto.PostRequest;
import com.example.blog_app.dto.PostResponse;
import com.example.blog_app.models.Post;
import com.example.blog_app.models.SubReddit;
import com.example.blog_app.models.UserObject;
import com.example.blog_app.repository.CommentRepo;
import com.example.blog_app.repository.VoteRepository;
import com.example.blog_app.service.AuthService;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private VoteRepository voteRepository;
    
    @Autowired
    private AuthService authService;

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "url", source = "postRequest.url")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subredditName", source = "subRedditName")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, SubReddit subRedditName, UserObject user);


    @Mapping(target = "postId", source = "postId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "url", source= "url")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "userName", source="user.username")
    @Mapping(target = "subredditName", source = "subredditName.name")
    @Mapping(target = "voteCount", source="voteCount")
    @Mapping(target = "commentCount", expression = "java(countCommentsByPost(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer countCommentsByPost(Post post) { 
        return commentRepo.findByPost(post).size();
    }
}
