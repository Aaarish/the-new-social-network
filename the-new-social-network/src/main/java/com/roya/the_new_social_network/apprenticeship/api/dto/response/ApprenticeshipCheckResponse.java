package com.roya.the_new_social_network.apprenticeship.api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprenticeshipCheckResponse {
    private boolean doesExist;
    private String apprenticeProfileId;
    private String mentorProfileId;

}
