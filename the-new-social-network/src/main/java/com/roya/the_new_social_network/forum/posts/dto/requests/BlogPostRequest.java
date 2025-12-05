package com.roya.the_new_social_network.forum.posts.dto.requests;

import com.roya.the_new_social_network.forum.media.Media;
import lombok.Getter;

import java.util.List;

@Getter
public class BlogPostRequest {
    private String title;
    private String category;
    private String content;
    private int readTime;
    private List<Media> media;

}
