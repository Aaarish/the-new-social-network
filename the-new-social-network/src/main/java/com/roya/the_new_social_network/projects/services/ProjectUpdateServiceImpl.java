package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.ProjectStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectUpdateServiceImpl implements ProjectUpdateService {
    private final ProjectDao projectDao;
    private final CommonDaoUtils utils;

    @Override
    public ProjectEntity updateProjectStatus(String projectId, String status) {
        ProjectEntity project = utils.returnProjectFromId(projectId);

        project.setStatus(ProjectStatus.valueOf(status));
        projectDao.save(project);

        return project;
    }
}
