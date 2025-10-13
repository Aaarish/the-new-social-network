package com.roya.the_new_social_network.profiles.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDeleteResponse {
    private boolean success;
    private String message;
    private String profileId;
    private LocalDateTime deletedAt;

}
