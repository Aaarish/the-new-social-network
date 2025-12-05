package com.roya.the_new_social_network.forum.posts.dto.requests;

import com.roya.the_new_social_network.forum.media.Media;
import lombok.Getter;

@Getter
public class PamphletPostRequest {
    private String mediaUrl;
    private Media media;
    private String caption;

}
