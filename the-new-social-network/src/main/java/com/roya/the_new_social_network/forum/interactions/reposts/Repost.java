package com.roya.the_new_social_network.forum.interactions.reposts;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reposts", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_user_post_repost",
                columnNames = {"post_id", "user_id"})
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "share_type")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Repost {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity user;

    @Column(name = "share_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ShareType shareType = ShareType.REPOST;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    enum ShareType {
        REPOST,
        QUOTE
    }

    public Repost(Post post, ProfileEntity user, ShareType shareType) {
        this.id = post.getPostId() + "_" + user.getProfileId();
        this.post = post;
        this.user = user;
        this.shareType = shareType;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isQuoteRepost() {
        return this.shareType.equals(ShareType.QUOTE);
    }

    public void setShareTypeToRepost() {
        this.shareType = ShareType.REPOST;
    }

}
