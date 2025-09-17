package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.global.access_management.ComponentType;
import com.roya.the_new_social_network.global.access_management.projects.ProjectAccessKey;
import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.applications.ApplicationDao;
import com.roya.the_new_social_network.projects.controllers.ProjectRequestDto;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectDao projectDao;
    private final ProjectMemberDao projectMemberDao;
    private final ApplicationDao applicationDao;

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

    @Override
    @Transactional
    public void approveApplication(Application application) {
        ProjectEntity project = application.getProject();
        ProjectMember applicant = application.getApplicant();

        applicant.setRole(ProjectRole.MEMBER);

        Set<ProjectAccessKey> memberAccessKeys = project.getAccessKeys().stream()
                .filter(accessKey -> accessKey.getComponentType().equals(ComponentType.DRAWER) ||
                        accessKey.getComponentType().equals(ComponentType.SHELF))
                .collect(Collectors.toSet());

        applicant.getAccessKeys().addAll(memberAccessKeys);
    }

    @Override
    @Transactional
    public void rejectApplication(Application application) {
        ProjectEntity project = application.getProject();
        ProjectMember applicant = application.getApplicant();

        projectMemberDao.delete(applicant);
        project.getApplications().remove(application);
        applicationDao.delete(application);
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
