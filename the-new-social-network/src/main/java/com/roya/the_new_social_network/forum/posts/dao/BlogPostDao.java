package com.roya.the_new_social_network.forum.posts.dao;

import com.roya.the_new_social_network.forum.posts.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostDao extends JpaRepository<BlogPost, String> {
}
