package com.roya.the_new_social_network.forum.interactions;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reposts")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Repost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity user;

    @Column(name = "share_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ShareType shareType = ShareType.REPOST;

//    @Column(name = "body", length = 1000)
//    @Setter
//    private String body;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    private enum ShareType {
        REPOST,
        QUOTE
    }

    public boolean isQuoteRepost() {
        return this.shareType.equals(ShareType.QUOTE);
    }
//
//    public boolean hasBody() {
//        return this.body != null && !this.body.trim().isEmpty();
//    }

}
