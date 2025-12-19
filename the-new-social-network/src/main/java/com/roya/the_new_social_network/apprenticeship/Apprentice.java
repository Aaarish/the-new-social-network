package com.roya.the_new_social_network.apprenticeship;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "apprentices")
@Getter
public class Apprentice {
    @Id
    @Column(name = "id", nullable = false)
    private String apprenticeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id", nullable = false)
    @Setter private Mentor mentor;

    private LocalDateTime createdAt;

    @Setter private LocalDateTime lastUpdatedAt;

    public Apprentice() {
    }

    public Apprentice(ProfileEntity profile, Mentor mentor) {
        this.profile = profile;
        this.apprenticeId = this.profile.getProfileId() + "-" + mentor.getMentorId();
        this.mentor = mentor;
        this.createdAt = LocalDateTime.now();
    }

}
