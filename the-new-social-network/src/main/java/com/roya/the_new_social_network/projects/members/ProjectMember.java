package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.access.ProjectAccessPolicyGroup;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_members")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @Setter private ProjectMemberRole role; // e.g., "creator", "applicant", "participant", "watcher"
    @Setter private String designation; // e.g., "developer", "designer", "manager"
//    private ProjectMembershipStatus status; // e.g., "active", "pending", "removed"

    @ManyToMany
    @Builder.Default
    private List<ProjectAccessPolicyGroup> accessPolicyGroup = new ArrayList<>();

    private LocalDateTime createdAt;
    @Setter private LocalDateTime lastUpdatedAt;


}
