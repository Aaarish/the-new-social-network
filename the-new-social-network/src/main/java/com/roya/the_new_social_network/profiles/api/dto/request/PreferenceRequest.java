package com.roya.the_new_social_network.profiles.api.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreferenceRequest {
    private String category;
    private Integer interestLevel;
    private String description;

}
