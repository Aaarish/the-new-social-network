package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.controllers.ProjectRequestDto;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberRequestDto;
import com.roya.the_new_social_network.projects.members.ProjectMemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectOrchestratorService {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    public ProjectEntity createProject(HttpServletRequest request, ProjectRequestDto requestDto) {
        // STEPS IN PROJECT CREATION

        // STEP 1: validate the request dto
        // STEP 2: create a project entry in db
        // STEP 3: create a project-member with role-type : creator
        // STEP 4: assign all the access-keys for the project
        // STEP 5: create a broadcast pamphlet (optional depending on profile settings)
        // ...

        ProjectEntity project = projectService.createProject(requestDto);

        ProjectMember projectMember = projectMemberService.addMember(project, ProjectMemberRequestDto.builder()
                .role(ProjectRole.CREATOR)
                .build());

        // the creator gets all the access-keys and does not need to be validated to access any component

//        broadcastPamphlet()

        return project;
    }

    private void validateProjectCreateRequest(String profileId, ProjectRequestDto requestDto) {
        if (!profileId.equals(requestDto.getCreatorId()))
            throw new RuntimeException("Unauthorized to create project for another profile");
    }

}
