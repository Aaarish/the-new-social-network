package com.roya.the_new_social_network.profiles.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private String profileId;
    private String username;
    private String name;
    private String bio;
    private Long contact;
    private String profileUrl;
    private int preferenceCount;
    private int postCount;
    private int projectMembershipCount;
    private int apprenticeCount;
    private int mentorCount;
    private int applicationCount;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

}
