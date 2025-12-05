package com.roya.the_new_social_network.forum.media;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "media")
@Getter
public class Media {
    @Id
    @Column(name = "id", nullable = false)
    private String mediaId;

    private String filename;
    private int fileSizeInBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProfileEntity owner;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    private String mimeType;
    private String pathUrl;
    private String source;
    private String visibility;

    private LocalDateTime createdAt;

    public Media() {}

    public Media(String name, int size, ProfileEntity owner, MediaType mediaType, String mimeType, String source, String visibility) {
        this.mediaId = UUID.randomUUID().toString();
        this.filename = name;
        this.fileSizeInBytes = size;
        this.owner = owner;
        this.mediaType = mediaType;
        this.mimeType = mimeType;
        this.source = source;
        this.visibility = visibility;
        this.createdAt = LocalDateTime.now();
    }

    public void linkToPost(Post post) {
        this.post = post;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

}
