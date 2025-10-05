package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.media.Media;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class MediaPost extends Post {
    @Column(name = "media_url", nullable = false)
    private String mediaUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    private List<Media> media; // media is optional in a media post, then it will be treated as a text-only post

    @Column(name = "body", nullable = false)
    private String caption;

}
