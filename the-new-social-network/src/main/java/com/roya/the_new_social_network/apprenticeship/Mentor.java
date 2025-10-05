package com.roya.the_new_social_network.apprenticeship;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mentor", orphanRemoval = true)
    @Builder.Default
    private List<Apprentice> apprentices = new ArrayList<>();

    private LocalDateTime createdAt;

    @Setter private LocalDateTime lastUpdatedAt;

    public void addApprentice(Apprentice apprentice) {
        if (apprentices == null) {
            apprentices = new ArrayList<>();
        }
        apprentices.add(apprentice);
        apprentice.setMentor(this);
    }

    public void removeApprentice(Apprentice apprentice) {
        if (apprentices != null) {
            apprentices.remove(apprentice);
            apprentice.setMentor(null);
        }
    }

    public int getApprenticeCount() {
        return apprentices != null ? apprentices.size() : 0;
    }

    public boolean hasApprentice(ProfileEntity profile) {
        if (apprentices == null || profile == null) {
            return false;
        }
        return apprentices.stream()
                .anyMatch(a -> a.getProfile().getProfileId().equals(profile.getProfileId()));
    }

}
