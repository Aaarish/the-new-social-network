package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "feeds")
public class FeedEntity {
    @Id
    private final String feedId;

    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    @OrderColumn(name = "position")
    private List<FeedPost> posts;

    private int size;

    private LocalDateTime lastUpdatedAt;

    private LocalDateTime oldestPostTime;

    private LocalDateTime newestPostTime;

    private boolean isLocked;

    public FeedEntity() {
        this.feedId = null;
    }

    public FeedEntity(ProfileEntity profile) {
        this.profile = profile;
        this.feedId = this.profile.getProfileId();
        this.posts = new ArrayList<>();
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void lockFeed() {
        this.isLocked = true;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public boolean isFeedFresh() {
        return this.newestPostTime != null && this.newestPostTime.isAfter(LocalDateTime.now().minusDays(1));
    }


    // to be used to recreate the feed
    public boolean isFeedStale() {
        return this.oldestPostTime != null && this.oldestPostTime.isBefore(LocalDateTime.now().minusDays(10));
    }

    public void addPost(Post post) {
        if (this.posts.size() > 50) {
            this.posts.removeLast();
        }

        this.posts.add(new FeedPost(post));
        this.newestPostTime = post.getCreatedAt();
        if (this.oldestPostTime == null || post.getCreatedAt().isBefore(this.oldestPostTime)) {
            this.oldestPostTime = post.getCreatedAt();
        }
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public int getSize() {
        return this.posts.size();
    }

}
