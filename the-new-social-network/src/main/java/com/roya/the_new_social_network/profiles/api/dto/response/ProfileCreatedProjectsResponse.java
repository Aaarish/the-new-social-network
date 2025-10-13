package com.roya.the_new_social_network.profiles.api.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileCreatedProjectsResponse {
    private String profileId;
    private String username;
    private List<CreatedProjectInfo> createdProjects;
    private long totalCreatedProjects;

}
