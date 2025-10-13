package com.roya.the_new_social_network.projects.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMemberResponse {
    private String memberId;
    private String profileId;
    private String projectId;
    private String role;
    private String designation;
    private LocalDateTime joinedAt;

}
