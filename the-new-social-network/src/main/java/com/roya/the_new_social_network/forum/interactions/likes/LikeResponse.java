package com.roya.the_new_social_network.forum.interactions.likes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponse {
    private String likeId;
    private String postId;
    private String userId;
    private LocalDateTime createdAt;

    public static LikeResponse fromEntity(Like like) {
        return LikeResponse.builder()
                .likeId(like.getLikeId())
                .postId(like.getPost().getPostId())
                .userId(like.getUser().getProfileId())
                .createdAt(like.getCreatedAt())
                .build();
    }

}
