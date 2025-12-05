package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.media.Media;
import com.roya.the_new_social_network.forum.posts.enums.PostType;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@DiscriminatorValue("PAMPHLET")
@Getter
public class PamphletPost extends Post {
    @Column(name = "media_url", nullable = false)
    private String mediaUrl;

    @OneToOne(fetch = FetchType.EAGER)
    private Media media;

    @Column(name = "caption", nullable = false)
    private String caption;

    public PamphletPost(ProfileEntity author, ProjectEntity project, PostVisibility visibility, ProjectPostLabel postLabel,
                        String mediaUrl, Media media, String caption) {
        super(author, PostType.PAMPHLET, project, visibility, postLabel);
        this.mediaUrl = mediaUrl;
        this.media = media;
        this.caption = caption;
    }

    public void editCaption(String newCaption) {
        this.caption = newCaption;
    }

    public void changeMediaUrl(String newMediaUrl) {
        this.mediaUrl = newMediaUrl;
    }

}
