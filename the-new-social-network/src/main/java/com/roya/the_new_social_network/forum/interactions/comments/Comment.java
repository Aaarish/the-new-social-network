package com.roya.the_new_social_network.forum.interactions.comments;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "comments")
@Getter
public class Comment {

    @Id
    private String id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "mainComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_comment_id")
    private Comment mainComment;

    private boolean isEdited;
    private LocalDateTime updatedAt;

    private int likeCount;
    private boolean isPinned;

    public Comment() {
    }

    public Comment(String content, ProfileEntity user, Post post) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.user = user;
        this.post = post;
        this.createdAt = LocalDateTime.now();
        this.replies = new ArrayList<>();
    }

    // basic methods

    public void likeComment() {
        this.likeCount++;
    }

    public void unlikeComment() {
        if (likeCount > 0) this.likeCount--;
    }

    public int getCommentLikes() {
        return this.likeCount;
    }

    public void editComment(String newContent) {
        this.content = newContent;
        this.isEdited = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void pinComment() {
        if (!isMainComment()) {
            throw new IllegalStateException("Only main comments can be pinned");
        }
        this.isPinned = true;
    }

    public void unpinComment() {
        if (this.isPinned) this.isPinned = false;
    }

    public boolean isMainComment() {
        return this.mainComment == null;
    }

    public boolean isReply() {
        return this.mainComment != null;
    }

    public Comment returnMainComment() {
        return this.isReply() ? this.mainComment : this;
    }

    public int returnRepliesCount() {
        return this.replies.size();
    }

    public List<Comment> returnReplies() {
        return this.replies;
    }

    public void addReply(Comment reply) {
        this.replies.add(reply);
        reply.mainComment = this;
    }

    public void removeReply(Comment reply) {
        if (this.replies != null) {
            this.replies.remove(reply);
            reply.mainComment = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
