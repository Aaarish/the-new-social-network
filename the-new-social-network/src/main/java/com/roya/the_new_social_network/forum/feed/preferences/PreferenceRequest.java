package com.roya.the_new_social_network.forum.feed.preferences;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceRequest {
    private String category;
    private Integer level;

}
