package com.roya.the_new_social_network.forum.media;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DiscriminatorValue("IMAGE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends Media {
    private String resolution;
    private String altText; // Alternative text for accessibility
    private int width;
    private int height;

}
