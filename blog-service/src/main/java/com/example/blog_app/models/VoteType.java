package com.example.blog_app.models;

public enum VoteType {
    UPVOTE(1),
    DOWNVOTE(-1);

    private int direction;
    VoteType(int direction) {}

    private Integer getDirection() {
        return direction;
    }
}
