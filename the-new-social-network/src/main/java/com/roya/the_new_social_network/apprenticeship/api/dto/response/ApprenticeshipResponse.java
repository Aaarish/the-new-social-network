package com.roya.the_new_social_network.apprenticeship.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprenticeshipResponse {
    private boolean success;
    private String message;
    private String apprenticeProfileId;
    private String mentorProfileId;
    private String action; // "CREATED", "REMOVED"
    private LocalDateTime timestamp;

}
