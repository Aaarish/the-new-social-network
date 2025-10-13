package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.api.dto.request.ProjectRequest;
import com.roya.the_new_social_network.projects.api.dto.response.ProjectResponse;
import com.roya.the_new_social_network.projects.members.ProjectMember;

import java.util.List;

public interface ProjectService {
    ProjectEntity createProject(ProjectRequest requestDto);

    ProjectEntity getProject(String projectId);

    List<ProjectResponse> getAllProjectsForProfile(String profileId);

    List<ProjectMember> getProjectMembers(String projectId);

    ProjectEntity updateProject(String profileId, String projectId, ProjectRequest requestDto);

    void deleteProject(String profileId, String projectId);

}
