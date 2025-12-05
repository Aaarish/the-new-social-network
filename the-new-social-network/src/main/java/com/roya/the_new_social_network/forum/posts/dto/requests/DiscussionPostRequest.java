package com.roya.the_new_social_network.forum.posts.dto.requests;

import lombok.Getter;

import java.util.List;

@Getter
public class DiscussionPostRequest {
    private String topic;
    private String content;
    private List<String> options;

}
