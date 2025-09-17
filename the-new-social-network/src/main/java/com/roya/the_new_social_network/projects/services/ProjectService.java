package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.controllers.ProjectRequestDto;

public interface ProjectService {
    ProjectEntity createProject(ProjectRequestDto requestDto);

    void updateProject(String profileId, String projectId, ProjectRequestDto requestDto);

    ProjectEntity viewProject(String projectId);

    void deleteProject(String profileId, String projectId);

    void approveApplication(Application application);
    void rejectApplication(Application application);

}
