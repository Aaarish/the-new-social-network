package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.profiles.preferences.Preference;

import java.util.List;

public class FeedEntity {
    private List<Post> posts;

    public FeedEntity(List<Post> posts) {
        this.posts = posts;
    }
}
