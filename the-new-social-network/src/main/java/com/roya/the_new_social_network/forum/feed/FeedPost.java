package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "feed_posts")
@Getter
public class FeedPost {
    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    private double preferenceMatchScore;
    private double recencyScore;
    private double engagementScore;
    private double diversityScore;       // to check how much categories the post covers
    private double totalScore;

    private boolean isLiked;
    private boolean isReposted;
    private LocalDateTime addedAt;

    public FeedPost() {
    }

    public FeedPost(Post post) {
        this.post = post;
        this.id = this.post.getPostId();
        this.addedAt = LocalDateTime.now();
    }

    public void setScores(double preferenceMatchScore, double recencyScore, double engagementScore, double diversityScore) {
        this.preferenceMatchScore = preferenceMatchScore;
        this.recencyScore = recencyScore;
        this.engagementScore = engagementScore;
        this.diversityScore = diversityScore;
        this.totalScore = preferenceMatchScore + recencyScore + engagementScore + diversityScore;
    }
}
