package com.roya.the_new_social_network.global.access_management.projects;

import com.roya.the_new_social_network.global.access_management.ComponentType;
import com.roya.the_new_social_network.global.access_management.Permission;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


// A set of 15 access-keys to be generated for a given project during the project creation step.

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectAccessKey {
    @Id
    @Builder.Default
    private String accessKeyId = generateAccessKey();

    @Enumerated(EnumType.STRING)
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, updatable = false)
    private ProjectEntity project;

    @Enumerated(EnumType.STRING)
    private ComponentType componentType;

    private String generateAccessKey() {
        if (project == null) {
            throw new RuntimeException("ERROR : project cannot be null");
        }

        int permissionId = permission == null ? 1 : permission.getPreference();
        int componentTypeId = componentType == null ? 1 : componentType.getTypeId();

        return permissionId*10 + componentTypeId + "@" + project.getProjectId();
    }

}
