package com.roya.the_new_social_network.profiles.preferences;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="preferences")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Preference {
    // this will be the blueprint for the recommendation engine and this will create a recommendation algorithm
    // that is well suited for the user and is in fact created by the user. This is a great way to counter the
    // currently prevalent recommendation algorithms that drains the users and lead to brain rot.

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String prefId = generatePreferenceId();

    private String generatePreferenceId() {
        return this.category.getPrefId() + "__" + this.getProfile().getProfileId();
    }

    @Enumerated(EnumType.STRING)
    private PreferenceCategory category;

    @Builder.Default
    @Setter private Integer interestLevel = 5; // 0 to 10

    @Setter private String description;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity profile;
}
