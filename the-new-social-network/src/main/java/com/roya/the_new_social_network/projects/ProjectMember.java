package com.roya.the_new_social_network.projects;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Setter private String role; // e.g., "creator", "applicant", "participant", "watcher"
    @Setter private String designation; // e.g., "developer", "designer", "manager"
    private String status; // e.g., "active", "pending", "removed"

    private LocalDateTime createdAt;
    @Setter private LocalDateTime lastUpdatedAt;

}
