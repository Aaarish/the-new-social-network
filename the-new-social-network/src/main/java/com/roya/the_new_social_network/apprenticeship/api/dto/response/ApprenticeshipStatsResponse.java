package com.roya.the_new_social_network.apprenticeship.api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprenticeshipStatsResponse {
    private String profileId;
    private int mentorCount;
    private int apprenticeCount;

}
