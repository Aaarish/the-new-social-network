package com.roya.the_new_social_network.projects.members;

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
