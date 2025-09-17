package com.roya.the_new_social_network.projects.controllers;

import com.roya.the_new_social_network.global.PreferenceCategory;
import com.roya.the_new_social_network.projects.ProjectEntity;
import lombok.*;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectRequestDto {
    private String title;
    private String description;
    private PreferenceCategory category;
    private String projectUrl;
    private String creatorId;

    public ProjectEntity toEntity() {
        return ProjectEntity.builder()
                .title(this.getTitle())
                .category(this.getCategory())
                .description(this.description)
                .projectUrl(this.getProjectUrl())
                .creatorId(this.getCreatorId())
                .build();
    }

}
