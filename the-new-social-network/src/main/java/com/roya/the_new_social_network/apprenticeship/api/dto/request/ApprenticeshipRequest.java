package com.roya.the_new_social_network.apprenticeship.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprenticeshipRequest {
    @NotBlank(message = "Source profile ID (apprentice) is required")
    private String sourceProfileId;

    @NotBlank(message = "Target profile ID (mentor) is required")
    private String targetProfileId;

    private String message; // Optional message explaining why they want this mentorship

}
