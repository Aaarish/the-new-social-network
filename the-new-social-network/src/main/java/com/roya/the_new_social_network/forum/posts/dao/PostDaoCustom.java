package com.roya.the_new_social_network.forum.posts.dao;

import com.roya.the_new_social_network.forum.posts.entities.Post;

import java.util.List;

public interface PostDaoCustom {

    List<Post> findFeedPostsForProfile(
            String profileId,
            int projectPostsLimit,
            int mentorPostsLimit,
            int mentorProjectPostsLimit,
            int categoryPostsLimit
    );
}
