package com.roya.the_new_social_network.projects.api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchStatusResponse {
    private boolean isWatching;
    private String profileId;
    private String projectId;

}
