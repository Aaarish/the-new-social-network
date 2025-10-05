package com.roya.the_new_social_network.projects;

import com.roya.the_new_social_network.drawers.Drawer;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.PreferenceCategory;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectRole;
import com.roya.the_new_social_network.shelves.Shelf;
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
    private String projectUrl;  // for ex :  github-url -> in case of a software project

    @Column(name = "creator_id", nullable = false, updatable = false)
    private String creatorId = getCreatorId();

    @Enumerated(EnumType.STRING)
    @Column(name = "project_joining_visibility", nullable = false)
    @Builder.Default
    private ProjectJoiningStrategy projectJoiningVisibility = ProjectJoiningStrategy.OPEN; // open, application, invite-only (to be implemented later)

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @Builder.Default
    private List<ProjectMember> projectMembers = new ArrayList<>(); //project members, project admins, project watchers

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    @Builder.Default
    private List<Application> applications = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    private List<Shelf> shelves;

    private List<Drawer> drawers;


    public String getCreatorId() {
        return this.getProjectMembers().stream()
                .filter(member -> member.getRole() == ProjectRole.OWNER)
                .findFirst()
                .map(member -> member.getProfile().getProfileId())
                .orElse(null);
    }


    /***
         private boolean isOpenForApplications; // default false
         forum
         workshop
         chat-rooms
         project-manager
         gallery
     ***/
}
