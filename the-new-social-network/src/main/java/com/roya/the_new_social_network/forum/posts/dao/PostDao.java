package com.roya.the_new_social_network.forum.posts.dao;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.PreferenceCategory;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface PostDao extends JpaRepository<Post, String> {

    List<Post> findByCategory(PreferenceCategory category);

//    List<Post> findByAuthorAndCreatedAtAfter(ProfileEntity author, LocalDateTime time);

//    List<Post> findByProjectAndCreatedAtAfter(ProjectEntity project, LocalDateTime time);


    @Query("select * from posts where user_id=?1 and created_at > time(?3-86400) order by created_at desc limit 20")
    List<Post> getPostsFromLastDay(ProfileEntity mentor, LocalDateTime timeInSeconds);

    @Query("select * from posts where project_id=?1 and created_at > time(?2-86400) order by created_at desc limit 20")
    List<Post> getProjectPostsFromLastDay(ProjectEntity project, LocalDateTime timeInSeconds);

    @Query("select * from posts where category in (?1) limit 10")
    List<Post> getPreferredPostsForFeed(Set<PreferenceCategory> preferences);
}
