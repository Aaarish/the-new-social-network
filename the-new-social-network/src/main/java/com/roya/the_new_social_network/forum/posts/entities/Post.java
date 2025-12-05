package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.interactions.comments.Comment;
import com.roya.the_new_social_network.forum.interactions.likes.Like;
import com.roya.the_new_social_network.forum.interactions.reposts.Repost;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type")
@Getter
@NoArgsConstructor @AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String postId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity author;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project; // optional (only has a value in case of a project post)

    @Enumerated(EnumType.STRING)
    private PreferenceCategory category; // optional

    private ProjectPostLabel projectPostLabel;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Repost> reposts;

    @Enumerated(EnumType.STRING)
    private PostVisibility visibility;

    private Integer likeCounts;
    private Integer commentCounts;
    private Integer shareCounts;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    public void likePost(Like like) {
        this.likes.add(like);
        this.likeCounts++;
    }

    public void unlikePost(Like like) {
        this.likes.remove(like);
        this.likeCounts--;
    }

}
