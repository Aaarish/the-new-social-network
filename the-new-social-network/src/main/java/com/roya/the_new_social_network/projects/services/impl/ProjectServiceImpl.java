package com.roya.the_new_social_network.projects.services.impl;

import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.api.dto.request.ProjectRequest;
import com.roya.the_new_social_network.projects.api.dto.request.ProjectUpdateRequest;
import com.roya.the_new_social_network.projects.api.dto.response.ProjectResponse;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectRole;
import com.roya.the_new_social_network.projects.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    private final CommonDaoUtils utils;

    @Override
    public ProjectEntity createProject(ProjectRequest requestDto) {
        ProjectEntity project = requestDto.toEntity();

        ProjectMember projectMember = ProjectMember.builder()
                .role(ProjectRole.OWNER)
                .designation("")
                .build();

        project.getProjectMembers().add(projectMember);
        return projectDao.save(project);
    }

    @Override
    @Transactional
    public ProjectEntity updateProject(String profileId, String projectId, ProjectUpdateRequest requestDto) {
        ValidRequestResponse valid = validateWriteRequest(profileId, projectId);

        if (valid.isAuthorized)
            throw new RuntimeException("Unauthorized to update this project");

        ProjectEntity project = valid.project;

        if (requestDto.getTitle() != null) project.setTitle(requestDto.getTitle());
        if (requestDto.getCategory() != null) project.setCategory(requestDto.getCategory());
        if (requestDto.getDescription() != null) project.setDescription(requestDto.getDescription());
        if (requestDto.getStatus() != null) project.setStatus(requestDto.getStatus());
        if (requestDto.getProjectJoiningStrategy() != null) project.setProjectJoiningVisibility(requestDto.getProjectJoiningStrategy());

        return projectDao.save(project);
    }

    @Override
    public ProjectEntity getProject(String projectId) {
        return returnIfProjectExists(projectId);
    }

    @Override
    public List<ProjectResponse> getAllProjectsForProfile(String profileId) {
        ProfileEntity profile = utils.returnProfileFromId(profileId);

        return profile.getProjectMembers().stream()
                .map(membership -> membership.getProject().toResponse())
                .toList();
    }

    @Override
    public List<ProjectMember> getProjectMembers(String projectId) {
        ProjectEntity project = returnIfProjectExists(projectId);
        return project.getProjectMembers();
    }

    @Override
    public void deleteProject(String profileId, String projectId) {
        ValidRequestResponse valid = validateWriteRequest(profileId, projectId);

        if (valid.isAuthorized)
            throw new RuntimeException("Unauthorized to update this project");

        projectDao.delete(valid.project);
    }

    // logic to ensure that the profile making the request is the creator of the project
    // similar logic is to be used to ensure that the profile making edit/delete request for the component or post should be its creator

    private ProjectEntity returnIfProjectExists(String projectId) {
        return projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    private record ValidRequestResponse (boolean isAuthorized, ProjectEntity project) {}

    private ValidRequestResponse validateWriteRequest(String profileId, String projectId) {
        // check if then given profile has the authorization to make this request for the given project
        ProjectEntity project = returnIfProjectExists(projectId);

        return new ValidRequestResponse(project.getCreatorId().equals(profileId), project);
    }

}
