package com.roya.the_new_social_network.forum.media;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DiscriminatorValue("AUDIO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Audio extends Media {
    private int duration; // Duration in seconds
    private String bitrate; // e.g., 320kbps

}
