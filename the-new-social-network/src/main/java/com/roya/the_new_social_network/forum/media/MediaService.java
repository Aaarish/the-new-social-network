package com.roya.the_new_social_network.forum.media;

import org.springframework.security.core.userdetails.UserDetails;

public interface MediaService {

    Media uploadMediaMetadata(MediaUploadRequest request, UserDetails userDetails);

    void processUploadedMediaForPost(String postId, String mediaId);

}
