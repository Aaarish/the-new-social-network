package com.roya.the_new_social_network.projects.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDeleteResponse {
    private boolean isSuccess;
    private String message;
    private String projectId;
    private String profileId;
    private LocalDateTime deletedAt;

}
