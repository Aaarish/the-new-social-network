package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.global.access_management.Permission;
import com.roya.the_new_social_network.global.access_management.projects.ProjectAccessKey;
import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany
    @JoinTable(
            name = "project_member_access_keys",
            joinColumns = @JoinColumn(name = "", referencedColumnName = "", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "", referencedColumnName = "", nullable = false)
    )
    @Builder.Default
    private Set<ProjectAccessKey> accessKeys = defaultAccessKeys();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter
    private LocalDateTime lastUpdatedAt;

    public ProfileEntity mapToProfile(ProjectMember projectMember) {
        return projectMember.getProfile();
    }

    public void giveAccessToMember(ProjectAccessKey accessKey) {
        this.getAccessKeys().add(accessKey);
    }

    private Set<ProjectAccessKey> defaultAccessKeys() {
        return this.getProject().getAccessKeys().stream()
                .filter(accessKey -> accessKey.getPermission().equals(Permission.READ))
                .collect(Collectors.toSet());
    }

}
