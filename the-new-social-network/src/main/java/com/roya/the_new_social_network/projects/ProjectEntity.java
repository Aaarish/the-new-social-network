package com.roya.the_new_social_network.projects;

import com.roya.the_new_social_network.forum.posts.Post;
import com.roya.the_new_social_network.global.PreferenceCategory;
import com.roya.the_new_social_network.global.access_management.ComponentType;
import com.roya.the_new_social_network.global.access_management.Permission;
import com.roya.the_new_social_network.global.access_management.projects.ProjectAccessKey;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.utils.ProjectUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

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
    @Setter private PreferenceCategory category;

    @Column(name = "project_url", unique = true)
    private String projectUrl;

    private String creatorId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @Builder.Default
    private List<ProjectMember> projectMembers = new ArrayList<>(); //project members, project admins, project watchers, project applicants

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @Builder.Default
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private Set<ProjectAccessKey> accessKeys = generateProjectAccessKeySet();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter private LocalDateTime lastUpdatedAt;

   /*
    to generate project access-keys set (15 access-keys).
    to be called when the project is created.
   */
    public Set<ProjectAccessKey> generateProjectAccessKeySet() {

        Set<ProjectAccessKey> projectAccessKeys = new HashSet<>();

        Map<Permission, ComponentType> map = ProjectUtils.populateProjectAccessMap();

        for (Map.Entry<Permission, ComponentType> entry : map.entrySet()) {
            ProjectAccessKey accessKey = ProjectAccessKey.builder()
                    .project(this)
                    .permission(entry.getKey())
                    .componentType(entry.getValue())
                    .build();

            projectAccessKeys.add(accessKey);
        }

        return projectAccessKeys;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    private List<Post> posts;

//    private boolean isOpenForApplications; // default false
    // forum
    // workshop
    // chat-rooms
    // project-manager
    // gallery
}
