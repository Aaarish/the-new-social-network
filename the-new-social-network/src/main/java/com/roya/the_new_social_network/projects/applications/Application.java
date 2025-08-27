package com.roya.the_new_social_network.projects.applications;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "applications")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Application {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String applicationId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @Setter private String status; // e.g., PENDING, APPROVED, REJECTED, EXPIRED

}
