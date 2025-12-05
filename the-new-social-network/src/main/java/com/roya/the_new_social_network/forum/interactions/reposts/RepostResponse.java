package com.roya.the_new_social_network.forum.interactions.reposts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepostResponse {
    private String repostId;
    private String postId;
    private String userId;
    private String shareType;
    private String createdAt;

    public static RepostResponse fromEntity(Repost repost) {
        if (repost == null) return null;

        return RepostResponse.builder()
                .repostId(repost.getRepostId())
                .postId(repost.getPost().getPostId())
                .userId(repost.getUser().getProfileId())
                .shareType(repost.getShareType().toString())
                .createdAt(repost.getCreatedAt().toString())
                .build();
    }

}
