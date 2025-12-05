package com.roya.the_new_social_network.forum.interactions.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment, String> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Comment c JOIN c.likeUserIds u WHERE c.id = :commentId AND u.id = :userId")
    boolean existsByCommentIdAndUserIdInLikeUserIds(@Param("commentId") String commentId, @Param("userId") String userId);

}
