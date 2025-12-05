package com.roya.the_new_social_network.forum.posts.dto.responses;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private String postId;
    private String authorId;
    private String projectId;
    private String postType;
    private String category;
    private String projectPostLabel;
    private String visibility;
    private int likesCount;
    private int commentsCount;
    private int repostsCount;
    private String createdAt;
    private String lastUpdatedAt;


    public static PostResponse fromEntity(Post post) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .authorId(post.getAuthor().getProfileId())
                .projectId(post.getProject().getProjectId())
                .postType(post.getPostType().toString())
                .category(post.getCategory().toString())
                .projectPostLabel(post.getProjectPostLabel().toString())
                .visibility(post.getVisibility().toString())
                .likesCount(post.getLikesCount())
                .commentsCount(post.getCommentsCount())
                .repostsCount(post.getRepostsCount())
                .createdAt(post.getCreatedAt().toString())
                .lastUpdatedAt(post.getLastUpdatedAt().toString())
                .build();
    }

}
