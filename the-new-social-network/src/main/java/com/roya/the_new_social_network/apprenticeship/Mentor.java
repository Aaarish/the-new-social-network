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
    @Column(name = "id", nullable = false)
    private String mentorId;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mentor", orphanRemoval = true)
    private List<Apprentice> apprentices;

    private LocalDateTime createdAt;

    @Setter private LocalDateTime lastUpdatedAt;

    public Mentor(ProfileEntity profile) {
        this.mentorId = profile.getProfileId();
        this.profile = profile;
        this.createdAt = LocalDateTime.now();
    }

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

    public boolean hasApprentice(String profileId) {
        if (apprentices == null || profile == null) {
            return false;
        }
        return apprentices.stream()
                .anyMatch(a -> a.getProfile().getProfileId().equals(profileId));
    }

}
