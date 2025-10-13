package com.roya.the_new_social_network.projects.api.dto.request;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.ProjectJoiningStrategy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @Size(max = 5000, message = "Description cannot exceed 5000 characters")
    private String description;

    private PreferenceCategory category;

    @Size(max = 500, message = "Project URL cannot exceed 500 characters")
    private String projectUrl;

    @NotBlank(message = "Creator profile ID is required")
    private String creatorProfileId;

    @lombok.Builder.Default
    private ProjectJoiningStrategy projectJoiningStrategy = ProjectJoiningStrategy.OPEN;

    public ProjectEntity toEntity() {
        return ProjectEntity.builder()
                .title(this.getTitle())
                .category(this.getCategory())
                .description(this.description)
                .projectUrl(this.getProjectUrl())
                .creatorId(this.getCreatorProfileId())
                .build();
    }

}
