package com.roya.the_new_social_network.profiles;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileResponse;
import com.roya.the_new_social_network.profiles.preferences.Preference;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.applications.Application;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "name")
    @Setter private String name;

    @Column(name = "email", unique = true)
    @Setter private String email;

    @Setter private String password;

    @Setter private Long contact;

    @Setter private String bio;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
    @Builder.Default
    Set<Preference> preferences = new HashSet<>();

    @Column(name = "profile_url", unique = true) // e.g., www.roya.com/username
    private String profileUrl;

//    @Column(name = "profile_visibility")
//    private ProfileVisibility profileVisibility;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter private LocalDateTime lastUpdatedAt;

    public int getApprenticeCount() {
        return apprentices != null ? apprentices.size() : 0;
    }

    public int getMentorCount() {
        return mentors != null ? mentors.size() : 0;
    }

    public int getProjectMembershipCount() {
        return projectMembers != null ? projectMembers.size() : 0;
    }

    public ProfileResponse toProfileResponse() {
        return ProfileResponse.builder()
                .profileId(this.getProfileId())
                .username(this.getUsername())
                .name(this.getName())
                .bio(this.getBio())
                .contact(this.getContact())
                .profileUrl(this.getProfileUrl())
                .preferenceCount(this.getPreferences() != null ? this.getPreferences().size() : 0)
                .postCount(this.getPosts() != null ? this.getPosts().size() : 0)
                .projectMembershipCount(this.getProjectMembershipCount())
                .apprenticeCount(this.getApprenticeCount())
                .mentorCount(this.getMentorCount())
                .applicationCount(this.getApplications() != null ? this.getApplications().size() : 0)
                .createdAt(this.getCreatedAt())
                .lastUpdatedAt(this.getLastUpdatedAt())
                .build();
    }

}
