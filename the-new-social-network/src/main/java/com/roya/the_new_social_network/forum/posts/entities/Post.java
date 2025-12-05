package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.forum.interactions.comments.Comment;
import com.roya.the_new_social_network.forum.interactions.likes.Like;
import com.roya.the_new_social_network.forum.interactions.reposts.Repost;
import com.roya.the_new_social_network.forum.posts.enums.PostType;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "POSTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type")
@Getter
public class Post {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String postId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project; // optional (only has a value in case of a project post)

    @Column(name = "post_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Enumerated(EnumType.STRING)
    private PreferenceCategory category; // optional

    @Enumerated(EnumType.STRING)
    private ProjectPostLabel projectPostLabel;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true)
    private List<Repost> reposts;

    @Enumerated(EnumType.STRING)
    private PostVisibility visibility;

    private Integer likesCount;
    private Integer commentsCount;
    private Integer repostsCount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    public Post() {}

    // general constructor
    public Post(ProfileEntity author, PostType postType) {
        this.postId = UUID.randomUUID().toString();
        this.author = author;
        this.postType = postType;
        this.createdAt = LocalDateTime.now();
    }

    // constructor for project posts
    public Post(ProfileEntity author, PostType postType, ProjectEntity project, PostVisibility visibility, ProjectPostLabel postLabel) {
        this.postId = UUID.randomUUID().toString();
        this.author = author;
        this.postType = postType;
        this.createdAt = LocalDateTime.now();
        this.project = project;
        this.visibility = visibility;
        this.projectPostLabel = postLabel;
    }

    public void addCategoryToPost(PreferenceCategory category) {
        this.category = category;
    }

    public void likePost(Like like) {
        this.likes.add(like);
        this.likesCount = this.likes.size();
    }

    public void unlikePost(Like like) {
        this.likes.remove(like);
        this.likesCount = this.likes.size();
    }

}
