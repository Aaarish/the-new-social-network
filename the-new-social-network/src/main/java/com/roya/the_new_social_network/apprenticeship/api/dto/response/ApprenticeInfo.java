package com.roya.the_new_social_network.apprenticeship.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprenticeInfo {
    private String apprenticeId;
    private String profileId;
    private String name;
    private String username;
    private LocalDateTime since;

}
