package com.example.blog_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.dto.VoteRequest;
import com.example.blog_app.service.VoteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/votes/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD})
@AllArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteRequest req) { 
        voteService.vote(req);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    
}
 