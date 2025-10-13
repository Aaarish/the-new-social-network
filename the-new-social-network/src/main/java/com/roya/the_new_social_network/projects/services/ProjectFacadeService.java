package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.api.dto.request.ProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectFacadeService {
    private final ProjectService projectService;
//    private final ProjectMemberService projectMemberService;

    public ProjectEntity createProject(String profileId, ProjectRequest requestDto) {
        validateProjectCreateRequest(profileId, requestDto);
        ProjectEntity project = projectService.createProject(requestDto);

//        create a broadcast pamphlet (optional depending on profile settings)

        return project;
    }

    private void validateProjectCreateRequest(String profileId, ProjectRequest requestDto) {
        if (!profileId.equals(requestDto.getCreatorId()))
            throw new RuntimeException("Unauthorized to create project for another profile");
    }

}
