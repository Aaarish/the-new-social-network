package com.roya.the_new_social_network.forum.interactions.comments;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CommentService {

    CommentResponse addComment(String content, String postId, UserDetails userDetails);

    CommentResponse editComment(String commentId, String content, UserDetails userDetails);

    GlobalDeleteResponse deleteComment(String commentId, UserDetails userDetails);

    List<CommentResponse> getCommentsOfPost(String postId, boolean includeReplies);

    List<Comment> getRecentCommentsOfPostCount(String postId, int count);

    CommentResponse addReply(String commentId, String content, UserDetails userDetails);

    List<Comment> getCommentReplies(String commentId);

    void likeComment(String commentId, UserDetails userDetails);

    void unlikeComment(String commentId, UserDetails userDetails);

//    void toggleLikeForComment(String commentId, UserDetails userDetails);

    void pinComment(String commentId, UserDetails userDetails);

    void unpinComment(String commentId, UserDetails userDetails);

//    Comment findCommentById(String commentId);

}
