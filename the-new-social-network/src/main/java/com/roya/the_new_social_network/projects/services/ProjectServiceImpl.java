package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.controllers.ProjectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectDao projectDao;

    @Override
    public ProjectEntity createProject(ProjectRequestDto requestDto) {
        return projectDao.save(requestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateProject(String profileId, String projectId, ProjectRequestDto requestDto) {
        if (validateRequest(profileId, projectId))
            throw new RuntimeException("Unauthorized to update this project");

        ProjectEntity project = returnIfProjectExists(projectId);

        if (requestDto.getTitle() != null) project.setTitle(requestDto.getTitle());
        if (requestDto.getCategory() != null) project.setCategory(requestDto.getCategory());
        if (requestDto.getDescription() != null) project.setDescription(requestDto.getDescription());
    }

    @Override
    public ProjectEntity viewProject(String projectId) {
        return returnIfProjectExists(projectId);
    }

    @Override
    public void deleteProject(String profileId, String projectId) {
        if (validateRequest(profileId, projectId))
            throw new RuntimeException("Unauthorized to update this project");

        ProjectEntity project = returnIfProjectExists(projectId);
        projectDao.delete(project);
    }

    private ProjectEntity returnIfProjectExists(String projectId) {
        return projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    private boolean validateRequest(String profileId, String projectId) {
        // check if then given profile has the authorization to make this request for the given project
        ProjectEntity project = returnIfProjectExists(projectId);

        return project.getCreatorId().equals(profileId);
    }
}
