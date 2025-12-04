package com.roya.the_new_social_network.projects.api.dto.request;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.projects.ProjectJoiningStrategy;
import com.roya.the_new_social_network.projects.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUpdateRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @Size(max = 5000, message = "Description cannot exceed 5000 characters")
    private String description;

    private PreferenceCategory category;

    private ProjectJoiningStrategy projectJoiningStrategy;

    private ProjectStatus status;

}
