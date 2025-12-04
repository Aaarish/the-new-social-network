package com.roya.the_new_social_network.forum.interactions.likes;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.dto.GlobalGenericResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface LikeService {

    LikeResponse likePost(String userId, String postId);

    GlobalDeleteResponse unlikePost(String postId, String userId);

    public GlobalGenericResponse toggleLike(String postId, UserDetails userDetails);

    List<LikeResponse> getLikesOfPost(String postId);

}
