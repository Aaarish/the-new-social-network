package com.roya.the_new_social_network.forum.interactions.comments;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeDao extends JpaRepository<CommentLike, String> {
    Optional<CommentLike> findByCommentAndUser(Comment comment, ProfileEntity user);

    @Query("SELECT cl FROM CommentLike cl WHERE cl.comment.id = :commentId AND cl.user.id = :userId")
    Optional<CommentLike> findByCommentIdAndUserId(@Param("commentId") String commentId, @Param("userId") String userId);

}
