package com.roya.the_new_social_network.global.access;

import com.roya.the_new_social_network.forum.interactions.comments.Comment;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalAccessValidationService {

    public void validateAccessToComment(ProfileEntity loggedInProfile, Comment comment) {
        if (!comment.getUser().getProfileId().equals(loggedInProfile.getProfileId())) {
            throw new SecurityException("User " + loggedInProfile.getProfileId() + " is not authorized to modify this comment");
        }
    }

    public void validateAccessToPost(ProfileEntity loggedInProfile, Post post) {
        if (!post.getAuthor().getProfileId().equals(loggedInProfile.getProfileId())) {
            throw new SecurityException("User " + loggedInProfile.getProfileId() + " is not authorized to modify this comment");
        }
    }

}
