package com.roya.the_new_social_network.profiles;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.forum.feed.FeedEntity;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileResponse;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.applications.Application;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

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

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "profile")
    private FeedEntity feed;

    Map<PreferenceCategory, Integer> preferencesMap;

    @Column(name = "profile_url", unique = true) // e.g., www.roya.com/username
    private String profileUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
    private List<Post> posts;

    //list of project memberships
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    private List<ProjectMember> projectMembers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    private List<Mentor> mentors;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    private List<Apprentice> apprentices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
    private List<Application> applications;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter private LocalDateTime lastUpdatedAt;

    // for minimal creation (to be used in signup)
    public ProfileEntity(String email, String password, String username) {
        this.profileId = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.username = username;
        this.preferencesMap = new EnumMap<>(PreferenceCategory.class);
        this.feed = new FeedEntity(this);
        this.createdAt = LocalDateTime.now();
    }

    public ProfileEntity(String username, String name, String email, String password, Long contact) {
        this.profileId = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.contact = contact;
        this.preferencesMap = new EnumMap<>(PreferenceCategory.class);
        this.feed = new FeedEntity(this);
        this.createdAt = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void setUsername(String username) {
        this.username = username;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void setContact(Long contact) {
        this.contact = contact;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void editBio(String newBio) {
        this.bio = newBio;
        this.lastUpdatedAt = LocalDateTime.now();
    }

//    public void followMentor(Mentor mentor) {
//        this.mentors.add(mentor);
//        mentor.getApprentices().add(new Apprentice(this, mentor));
//        this.lastUpdatedAt = LocalDateTime.now();
//    }
//
//    public void unfollowMentor(Mentor mentor) {
//        this.mentors.remove(mentor);
//        mentor.getApprentices().removeIf(a -> a.getProfile().getProfileId().equals(this.profileId));
//        this.lastUpdatedAt = LocalDateTime.now();
//    }

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
