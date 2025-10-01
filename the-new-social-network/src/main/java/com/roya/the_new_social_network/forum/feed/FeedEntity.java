package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.profiles.preferences.Preference;

import java.util.List;

public class FeedEntity {
    private ProfileEntity profile;

    private String feedId = profile.getProfileId();

    private List<Preference> preferences;

    private List<Post> posts;

}
