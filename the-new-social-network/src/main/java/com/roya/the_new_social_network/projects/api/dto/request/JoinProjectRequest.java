package com.roya.the_new_social_network.projects.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinProjectRequest {
    @NotBlank(message = "Profile ID is required")
    private String profileId;

    private String message; // Optional message for application-based projects
}
