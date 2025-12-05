package com.roya.the_new_social_network.forum.posts.apis;

import com.roya.the_new_social_network.forum.posts.dto.requests.MediaPostRequest;
import com.roya.the_new_social_network.forum.posts.services.PostService;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MediaPostController {
    private final PostService postService;
    private final CommonDaoUtils daoUtils;

    public ResponseEntity<Void> createMediaPost(MultipartFile mediaFile, MediaPostRequest request, UserDetails userDetails) {
        ProfileEntity user = daoUtils.returnProfileFromUsername(userDetails.getUsername());
        postService.createMediaPost(user.getProfileId(), request);

        // upload mediaFile to S3

        return ResponseEntity.ok().build();
    }

}
