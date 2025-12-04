package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.media.Media;
import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@DiscriminatorValue("BLOG")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost extends Post {
    private String title;
    private String category;
    private String body;
    private int readTime;
    private List<Media> media;

}
