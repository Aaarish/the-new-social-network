package com.roya.the_new_social_network.projects.api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchResponse {
    private boolean isSuccess;
    private String message;
    private String projectId;
    private String profileId;
    private Boolean watching; // null for toggle, true for watch, false for unwatch

}
