package com.roya.the_new_social_network.projects.members;

import com.roya.the_new_social_network.projects.ProjectEntity;

public interface ProjectMemberService {
    ProjectMember addMember(ProjectEntity project, ProjectMemberRequestDto requestDto);

}
