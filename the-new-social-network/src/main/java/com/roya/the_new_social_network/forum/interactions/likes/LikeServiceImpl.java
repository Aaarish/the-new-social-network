package com.roya.the_new_social_network.forum.interactions.likes;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.dto.GlobalGenericResponse;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LikeServiceImpl implements LikeService {
    private final LikeDao likeDao;
    private final CommonDaoUtils utils;

    @Override
    @Transactional
    public LikeResponse likePost(String postId, String userId) {
        if (hasUserLikedPost(postId, userId)) {
            throw new IllegalStateException("User has already liked this post");
        }

        Post post = utils.returnPostFromId(postId);
        ProfileEntity user = utils.returnProfileFromId(userId);

        Like like = likeDao.save(new Like(post, user));
        post.likePost(like);

        log.info("Post {} liked by user {}", postId, userId);

        return LikeResponse.fromEntity(like);
    }

    @Override
    @Transactional
    public GlobalDeleteResponse unlikePost(String postId, String userId) {
        Post post = utils.returnPostFromId(postId);
        String likeId = postId + "_" + userId;

        Like like = likeDao.findById(likeId)
                        .orElseThrow(() -> new IllegalStateException("Like not found for the given post and user"));

        post.unlikePost(like);
        likeDao.delete(like);

        log.info("Post {} unliked by user {}", postId, userId);

        return GlobalDeleteResponse.builder()
                .success(true)
                .resourceId(like.getLikeId())
                .message("Like successfully removed")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional
    public GlobalGenericResponse toggleLike(String postId, UserDetails userDetails) {
        ProfileEntity user = utils.returnProfileFromUsername(userDetails.getUsername());

        if (likeDao.existsByPostIdAndUserId(postId, user.getProfileId())) {
            return GlobalGenericResponse.generateSuccessResponse(
                    unlikePost(postId, user.getProfileId()), "Successfully unliked the post");
        } else {
            return GlobalGenericResponse.generateSuccessResponse(
                    likePost(postId, user.getProfileId()), "Successfully liked the post");
        }
    }

    @Override
    public List<LikeResponse> getLikesOfPost(String postId) {
        Post post = utils.returnPostFromId(postId);

        return post.getLikes().stream()
                .map(LikeResponse::fromEntity)
                .toList();
    }

    public boolean hasUserLikedPost(String postId, String userId) {
        return likeDao.existsByPostIdAndUserId(postId, userId);
    }
}
