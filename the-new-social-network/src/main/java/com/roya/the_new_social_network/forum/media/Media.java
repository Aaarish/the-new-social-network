package com.roya.the_new_social_network.forum.media;

import com.roya.the_new_social_network.forum.posts.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "media")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String mediaId;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private MediaType mediaType;

    private String url;

    private int fileSize;

    private int length; // for audio and video

}
