package com.roya.the_new_social_network.projects;

import com.roya.the_new_social_network.projects.access.ProjectAccessPolicy;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String projectId;

    @Column(name = "title", nullable = false)
    @Setter private String title;
    @Setter private String description;

    @Column(name = "project_url", unique = true)
    private String projectUrl;

    private String ownerId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @Builder.Default
    private List<ProjectMember> projectMembers = new ArrayList<>(); //project participants, project watchers, project applicants

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @Builder.Default
    private List<Application> applications = new ArrayList<>();

    private LocalDateTime createdAt;
    @Setter private LocalDateTime lastUpdatedAt;

    // forum
    // workshop
    // chat-rooms
    // project-manager
    // gallery
}
