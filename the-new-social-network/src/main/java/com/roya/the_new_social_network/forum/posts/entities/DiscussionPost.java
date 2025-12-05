package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.posts.enums.PostType;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@DiscriminatorValue("DISCUSSION")
@Getter
public class DiscussionPost extends Post {
    private String topic;
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscussionOption> options;

    public DiscussionPost(ProfileEntity author, String topic, String content, List<DiscussionOption> options) {
        super(author, PostType.DISCUSSION);
        this.topic = topic;
        this.content = content;
        this.options = options;
    }

    public DiscussionPost(ProfileEntity author, ProjectEntity project, PostVisibility visibility, ProjectPostLabel postLabel,
                          String topic, String content, List<DiscussionOption> options) {
        super(author, PostType.DISCUSSION, project, visibility, postLabel);
        this.topic = topic;
        this.content = content;
        this.options = options;
    }

    public void addOption(String option) {
        if (this.options.size() > 5) {
            return;
        }

        this.options.add(new DiscussionOption(this.options.size()+1, option));
    }

}
