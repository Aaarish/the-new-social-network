package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.media.Media;
import com.roya.the_new_social_network.forum.posts.enums.PostType;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;

import java.util.List;


@DiscriminatorValue("BLOG")
@Getter
public class BlogPost extends Post {
    private String title;
    private String category;
    private String content;
    private int readTime;
    private List<Media> media;

    public BlogPost(ProfileEntity author, String title, String category, String content, int readTime, List<Media> media) {
        super(author, PostType.BLOG);
        this.title = title;
        this.category = category;
        this.content = content;
        this.readTime = readTime;
        this.media = media;
    }

    public BlogPost(ProfileEntity author, ProjectEntity project, PostVisibility visibility, ProjectPostLabel postLabel,
                    String title, String category, String content, int readTime, List<Media> media) {
        super(author, PostType.BLOG, project, visibility, postLabel);
        this.title = title;
        this.category = category;
        this.content = content;
        this.readTime = readTime;
        this.media = media;
    }

    public BlogPost(ProfileEntity author, String title, String category, String content, List<Media> media) {
    }
}
