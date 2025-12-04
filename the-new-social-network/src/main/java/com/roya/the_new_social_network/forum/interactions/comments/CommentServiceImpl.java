package com.roya.the_new_social_network.forum.interactions.comments;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.access.GlobalAccessValidationService;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final CommonDaoUtils utils;
    private final GlobalAccessValidationService accessValidationService;

    @Override
    @Transactional
    public CommentResponse addComment(String content, String postId, UserDetails userDetails) {
        validateCommentRequest(content);

        ProfileEntity user = utils.returnProfileFromUsername(userDetails.getUsername());
        Post post = utils.returnPostFromId(postId);

        log.info("Adding comment to post {} by user {}", postId, user.getProfileId());

        Comment comment = new Comment(content, user, post);
        Comment savedComment = commentDao.save(comment);

        log.info("Comment added to post {} by user {}", postId, user.getProfileId());

        return CommentResponse.fromEntity(savedComment);
    }

    @Override
    @Transactional
    public CommentResponse editComment(String commentId, String content, UserDetails userDetails) {
        validateCommentRequest(content);
        Comment comment = findCommentById(commentId);

        ProfileEntity profile = utils.returnProfileFromUsername(userDetails.getUsername());
        accessValidationService.validateAccessToComment(profile, comment);

        log.info("Editing comment {} by user {}", commentId, profile.getProfileId());

        comment.editComment(content);
        Comment editedComment = commentDao.save(comment);

        log.info("Comment {} edited by user {}", commentId, profile.getProfileId());
        return CommentResponse.fromEntity(editedComment);
    }

    @Override
    @Transactional
    public GlobalDeleteResponse deleteComment(String commentId, UserDetails userDetails) {
        ProfileEntity user = utils.returnProfileFromUsername(userDetails.getUsername());
        Comment comment = findCommentById(commentId);

        log.info("Deleting comment {} by user {}", commentId, user.getProfileId());

        accessValidationService.validateAccessToComment(user, comment);
        commentDao.delete(comment);

        log.info("Comment {} deleted by user {}", commentId, user.getProfileId());

        return GlobalDeleteResponse.builder()
                .success(true)
                .resourceId(commentId)
                .message("Comment deleted successfully")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public List<CommentResponse> getCommentsOfPost(String postId, boolean includeReplies) {
        log.debug("Fetching comments for post {}", postId);

        Post post = utils.returnPostFromId(postId);

        return post.getComments().stream()
                .map(comment -> CommentResponse.fromEntity(comment, includeReplies))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> getRecentCommentsOfPostCount(String postId, int count) {
        return null;
    }

    @Override
    @Transactional
    public CommentResponse addReply(String commentId, String content, UserDetails userDetails) {
        validateCommentRequest(content);
        ProfileEntity user = utils.returnProfileFromId(userDetails.getUsername());
        Comment comment = findCommentById(commentId);

        log.info("Adding reply to comment {} by user {}", commentId, userDetails.getUsername());

        comment.addReply(new Comment(content, user, comment.getPost()));
        Comment savedReply = commentDao.save(comment);

        log.info("Reply {} added to comment {} by user {}", savedReply.getId(), commentId, userDetails.getUsername());
        return CommentResponse.fromEntity(savedReply);
    }

    @Override
    public List<Comment> getCommentReplies(String commentId) {
        Comment comment = findCommentById(commentId);
        return comment.returnReplies();
    }

    @Override
    @Transactional
    public void likeComment(String commentId, UserDetails userDetails) {
        Comment comment = findCommentById(commentId);
        comment.likeComment();
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public void unlikeComment(String commentId, UserDetails userDetails) {
        Comment comment = findCommentById(commentId);
        comment.likeComment();
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public void pinComment(String commentId, UserDetails userDetails) {
        Comment comment = findCommentById(commentId);
        ProfileEntity profile = utils.returnProfileFromUsername(userDetails.getUsername());
        Post post = comment.getPost();

        accessValidationService.validateAccessToPost(profile, post);

        comment.pinComment();
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public void unpinComment(String commentId, UserDetails userDetails) {
        Comment comment = findCommentById(commentId);
        ProfileEntity profile = utils.returnProfileFromUsername(userDetails.getUsername());
        Post post = comment.getPost();

        accessValidationService.validateAccessToPost(profile, post);

        comment.unpinComment();
        commentDao.save(comment);
    }

    private Comment findCommentById(String commentId)  {
        return commentDao.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    private void validateCommentRequest(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        if (content.length() > 500) {
            throw new IllegalArgumentException("Comment exceeds maximum length");
        }
    }

}
