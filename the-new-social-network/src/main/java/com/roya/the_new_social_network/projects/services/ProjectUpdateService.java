package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.projects.ProjectEntity;

public interface ProjectUpdateService {
    ProjectEntity updateProjectStatus(String projectId, String status);

}
