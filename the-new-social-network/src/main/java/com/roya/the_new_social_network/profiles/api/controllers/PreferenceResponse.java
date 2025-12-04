package com.roya.the_new_social_network.profiles.api.controllers;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceResponse {
    private String preferenceId;
    private String profileId;
    private PreferenceCategory category;
    private Integer interestLevel;
    private String description;

}
