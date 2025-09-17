package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import lombok.*;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectMemberRequestDto {
    private ProjectRole role;
    private String designation;

    public ProjectMember toEntity() {
        return ProjectMember.builder()
                .role(this.getRole())
                .designation(this.designation)
                .build();
    }
}
