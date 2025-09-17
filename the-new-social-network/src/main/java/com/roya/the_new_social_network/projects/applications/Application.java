package com.roya.the_new_social_network.projects.applications;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "applications")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String applicationId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @OneToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private ProjectMember applicant;

    @Enumerated(EnumType.STRING)
    @Setter private ApplicationStatus status; // e.g., PENDING, APPROVED, REJECTED, EXPIRED

}
