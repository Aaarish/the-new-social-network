package com.roya.the_new_social_network.projects;

import com.roya.the_new_social_network.drawers.entities.Drawer;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.projects.api.dto.response.ProjectResponse;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.shelves.Shelf;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "projects")
@Getter
public class ProjectEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String projectId;

    @Column(name = "title", nullable = false)
    private String title;

    private String description;

    private PreferenceCategory category;

    @Column(name = "project_url", unique = true)
    private String projectUrl;  // for ex :  github-url -> in case of a software project

    @Column(name = "creator_id", nullable = false, updatable = false)
    private String creatorId;

    @Column(name = "project_joining_visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectJoiningStrategy projectJoiningVisibility;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    private List<ProjectMember> projectMembers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "project", orphanRemoval = true)
    private List<Application> applications;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime lastUpdatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project", orphanRemoval = true)
    private List<Shelf> shelves;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drawer> drawers;

    public ProjectEntity() {
    }

    public ProjectEntity(String title, String description, PreferenceCategory category, String creatorId) {
        this.projectId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.category = category;
        this.creatorId = creatorId;
        this.projectJoiningVisibility = ProjectJoiningStrategy.OPEN;
        this.status = ProjectStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public void editProjectDetails(Object details) {
        List<String> detailList = (List<String>) details;

        if (!detailList.isEmpty()) {
            this.title = detailList.get(0);
            if (detailList.size() > 1) this.description = detailList.get(1);
            if (detailList.size() > 2) this.category = PreferenceCategory.valueOf(detailList.get(2));
            this.lastUpdatedAt = LocalDateTime.now();
        }
    }

    public void addProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void changeStatus(ProjectStatus newStatus) {
        this.status = newStatus;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void changeJoiningVisibility(ProjectJoiningStrategy newVisibility) {
        this.projectJoiningVisibility = newVisibility;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void addApplication(Application application) {
        if (this.applications == null) {
            this.applications = new ArrayList<>();
        }

        this.applications.add(application);
    }

    public void addPost(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }

        this.posts.add(post);
    }

    public ProjectResponse toResponse() {
        return ProjectResponse.builder()
                .projectId(this.getProjectId())
                .title(this.getTitle())
                .description(this.getDescription())
                .category(this.getCategory())
                .projectUrl(this.getProjectUrl())
                .creatorId(this.creatorId)
                .projectJoiningStrategy(this.getProjectJoiningVisibility())
                .memberCount(this.getProjectMembers() != null ? this.getProjectMembers().size() : 0)
                .applicationCount(this.getApplications() != null ? this.getApplications().size() : 0)
                .createdAt(this.getCreatedAt())
                .lastUpdatedAt(this.getLastUpdatedAt())
                .build();
    }


    /***
         forum
         workshop
         chat-rooms
         project-manager
     ***/
}
