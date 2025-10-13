package com.roya.the_new_social_network.profiles.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileStatsResponse {
    private String profileId;
    private int totalPosts;
    private int totalProjects;
    private int totalApprentices;
    private int totalMentors;
    private int totalApplications;
    private LocalDateTime joinedDate;

}
