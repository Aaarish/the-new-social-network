package com.roya.the_new_social_network.forum.interactions.likes;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeDao extends JpaRepository<Like, String> {

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Like l WHERE l.post.id = :postId AND l.user.id = :userId")
    boolean existsByPostIdAndUserId(@Param("postId") String postId, @Param("userId") String userId);

    Optional<Like> findByPostAndUser(Post post, ProfileEntity user);
}
