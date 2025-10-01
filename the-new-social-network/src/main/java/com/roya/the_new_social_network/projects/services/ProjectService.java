package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.controllers.ProjectRequestDto;

public interface ProjectService {
    ProjectEntity createProject(ProjectRequestDto requestDto);

    ProjectEntity viewProject(String projectId);

    void updateProject(String profileId, String projectId, ProjectRequestDto requestDto);

    void deleteProject(String profileId, String projectId);

}
