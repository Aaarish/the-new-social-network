package com.roya.the_new_social_network.forum.interactions.comments;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {
    @JsonProperty(value = "comment_id")
    private String id;

    @JsonProperty(value = "comment")
    private String content;

    @JsonProperty(value = "user")
    private UserSummary user;

    @JsonProperty(value = "post_id")
    private String postId;

    @JsonProperty(value = "main_comment_id")
    private String mainCommentId;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty(value = "is_edited")
    private boolean isEdited;

    @JsonProperty(value = "is_pinned")
    private boolean isPinned;

    @JsonProperty(value = "is_reply")
    private boolean isReply;

    @JsonProperty(value = "like_count")
    private int likeCount;

    @JsonProperty(value = "reply_count")
    private int replyCount;

    @JsonProperty(value = "is_liked_by_current_user")
    private boolean isLikedByCurrentUser;

    @JsonProperty(value = "replies")
    private List<CommentResponse> replies;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummary {
        private String id;
        private String username;
        private String displayName;
        private String profilePictureUrl;
    }

    public static CommentResponse fromEntity(Comment comment) {
        return fromEntity(comment, false);
    }

    // Factory method with optional replies
    public static CommentResponse fromEntity(Comment comment, boolean includeReplies) {
        CommentResponseBuilder builder = CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .user(UserSummary.builder()
                        .id(comment.getUser().getProfileId())
                        .username(comment.getUser().getUsername())
                        .displayName(comment.getUser().getName())
//                        .profilePictureUrl(comment.getUser().get)
                        .build())
                .postId(comment.getPost().getPostId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .isEdited(comment.isEdited())
                .isPinned(comment.isPinned())
                .isReply(comment.isReply())
                .likeCount(comment.getLikeCount())
                .replyCount(comment.returnRepliesCount());

        // Add main comment ID if this is a reply
        if (comment.isReply()) {
            builder.mainCommentId(comment.getMainComment().getId());
        }

        // Add replies if requested and this is a main comment
        if (includeReplies && comment.isMainComment()) {
            List<CommentResponse> replyResponses = comment.getReplies().stream()
                    .map(reply -> CommentResponse.fromEntity(reply, false))
                    .collect(Collectors.toList());
            builder.replies(replyResponses);
        }

        return builder.build();
    }

}
