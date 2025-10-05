package com.roya.the_new_social_network.apprenticeship;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "apprentices")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Apprentice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String apprenticeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id", nullable = false)
    @Setter private Mentor mentor;

    private LocalDateTime createdAt;

    @Setter private LocalDateTime lastUpdatedAt;

}
