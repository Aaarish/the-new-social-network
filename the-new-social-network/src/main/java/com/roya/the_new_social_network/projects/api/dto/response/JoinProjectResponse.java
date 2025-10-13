package com.roya.the_new_social_network.projects.api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinProjectResponse {
    private boolean success;
    private String message;
    private String projectId;
    private String profileId;

}
