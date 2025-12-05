package com.roya.the_new_social_network.forum.interactions.comments;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comment_likes", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_user_comment_like",
                columnNames = {"comment_id", "user_id"})
})
@Getter
public class CommentLike {
    @Id
    private String commentLikeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity user;

    private LocalDateTime createdAt;

    public CommentLike() {
    }

    public CommentLike(Comment comment, ProfileEntity user) {
        this.commentLikeId = comment.getCommentId() + "_" + user.getProfileId();
        this.comment = comment;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentLike that)) return false;
        return Objects.equals(user, that.user) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, comment);
    }

}
