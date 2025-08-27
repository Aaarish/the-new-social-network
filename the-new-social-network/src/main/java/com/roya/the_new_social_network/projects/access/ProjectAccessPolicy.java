package com.roya.the_new_social_network.projects.access;

import com.roya.the_new_social_network.common.access.Permission;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "project_access")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectAccessPolicy {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String projectAccessPolicyId; // projectId + delimiter + resourceId

    @Setter private Permission permission; // e.g., "read", "write"

    @Builder.Default
    private Map<ProjectAccessLevel, String> resourceMap = new HashMap<>(); // '{resourceName : resourceId}' '{"shelf" : shelfId}', '{"project" : projectId}'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    @Setter private ProjectAccessPolicyGroup accessPolicyGroup;

}
