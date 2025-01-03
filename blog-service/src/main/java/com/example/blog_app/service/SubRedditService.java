package com.example.blog_app.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.example.blog_app.dto.SubRedditRequest;
import com.example.blog_app.mapper.SubRedditMapper;
import com.example.blog_app.repository.SubRedditRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "subredditCache")
public class SubRedditService {

    private final SubRedditRepo subRedditRepo;
    private final SubRedditMapper subRedditMapper;
    /**
     * 
     * @param newSubReddit
     */
    @Transactional
    public SubRedditRequest saveSubReddit(SubRedditRequest newSubReddit) { 
        var toSave = subRedditRepo.save(subRedditMapper.mapDtoToSubReddit(newSubReddit));

        // Get id from database
        newSubReddit.setId(toSave.getId());
        return newSubReddit;
    }
    /**
     * 
     * @return
     */
    @Cacheable(cacheNames = "subreddit")
    @Transactional(readOnly = true)
    public List<SubRedditRequest> getAll() {
        return subRedditRepo.findAll()
            .stream()
            .map(subRedditMapper::mapSubRedditToDto)
            .collect(Collectors.toList());
    }
    /**
     * 
     * @param subreddit1
     * @return
     */
    // private SubRedditDto mapToDto(SubReddit subreddit) {
    //     return SubRedditDto.builder()
    //         .name(subreddit.getName())
    //         .id(subreddit.getId())
    //         .numberOfPosts(subreddit.getPosts().size())
    //         .build();
    // }
}
