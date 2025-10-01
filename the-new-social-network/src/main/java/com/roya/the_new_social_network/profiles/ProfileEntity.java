package com.roya.the_new_social_network.profiles;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.preferences.Preference;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.applications.Application;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "profiles")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String profileId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Setter private String password;
    private Long contact;
    @Setter private String bio;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
    @Builder.Default
    Set<Preference> preferences = new HashSet<>();

    @Column(name = "profile_url", unique = true) // e.g., www.roya.com/username
    private String profileUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    private List<Post> posts;

    //list of project memberships
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    @Builder.Default
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    @Builder.Default
    private List<Mentor> mentors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    @Builder.Default
    private List<Apprentice> apprentices = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    @Builder.Default
    private List<Application> applications = new ArrayList<>();

    private LocalDateTime createdAt;
    @Setter private LocalDateTime lastUpdatedAt;

}
