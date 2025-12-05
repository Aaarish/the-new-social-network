package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.posts.enums.PostType;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;

@DiscriminatorValue("TEXT")
@Getter
public class TextPost extends Post {
    @Column(name = "content", nullable = false)
    private String content;

    public TextPost(ProfileEntity author, String content) {
        super(author, PostType.TEXT);
        this.content = content;
    }

    public TextPost(ProfileEntity author, ProjectEntity project, PostVisibility visibility, ProjectPostLabel postLabel,
                    String content) {
        super(author, PostType.TEXT, project, visibility, postLabel);
        this.content = content;
    }

    public void editContent(String content) {
        this.content = content;
    }

}
