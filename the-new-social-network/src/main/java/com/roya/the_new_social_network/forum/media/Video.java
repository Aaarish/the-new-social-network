package com.roya.the_new_social_network.forum.media;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DiscriminatorValue("VIDEO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video extends Media {
    private int duration; // Duration in seconds
    private String resolution; // e.g., 1080p, 4K
    private int frameRate; // e.g., 30fps, 60fps
    private int width;
    private int height;

}
