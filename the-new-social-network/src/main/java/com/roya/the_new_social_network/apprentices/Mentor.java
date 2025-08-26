package com.roya.the_new_social_network.apprentices;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mentors")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String mentorId;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "mentor", orphanRemoval = true)
    private List<Apprentice> apprentices;

    private LocalDateTime createdAt;
    @Setter private LocalDateTime lastUpdatedAt;

}
