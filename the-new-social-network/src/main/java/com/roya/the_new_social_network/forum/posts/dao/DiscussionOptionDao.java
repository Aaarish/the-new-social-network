package com.roya.the_new_social_network.forum.posts.dao;

import com.roya.the_new_social_network.forum.posts.entities.DiscussionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionOptionDao extends JpaRepository<DiscussionOption, String> {
}
