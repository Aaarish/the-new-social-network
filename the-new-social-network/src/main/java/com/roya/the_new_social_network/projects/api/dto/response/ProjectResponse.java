package com.roya.the_new_social_network.projects.api.dto.response;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.projects.ProjectJoiningStrategy;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {
    private String projectId;
    private String title;
    private String description;
    private PreferenceCategory category;
    private String projectUrl;
    private String creatorId;
    private ProjectJoiningStrategy projectJoiningStrategy;
    private int memberCount;
    private int applicationCount;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

}
