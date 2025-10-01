package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Setter private ProjectRole role; // e.g., "creator", "applicant", "member", "admin", "watcher"
    @Setter private String designation; // e.g., "developer", "designer", "manager"

//    private ProjectMembershipStatus status; // e.g., "active", "pending", "removed"

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter
    private LocalDateTime lastUpdatedAt;

    public ProfileEntity mapToProfile(ProjectMember projectMember) {
        return projectMember.getProfile();
    }

}
