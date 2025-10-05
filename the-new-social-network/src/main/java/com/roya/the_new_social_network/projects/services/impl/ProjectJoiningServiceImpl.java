package com.roya.the_new_social_network.projects.services.impl;

import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.ProjectJoiningStrategy;
import com.roya.the_new_social_network.projects.applications.Application;
import com.roya.the_new_social_network.projects.applications.ApplicationDao;
import com.roya.the_new_social_network.projects.applications.ApplicationStatus;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import com.roya.the_new_social_network.projects.members.ProjectRole;
import com.roya.the_new_social_network.projects.services.ProjectJoiningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProjectJoiningServiceImpl implements ProjectJoiningService {
    private final ProjectDao projectDao;
    private final ProfileDao profileDao;
    private final ProjectMemberDao projectMemberDao;
    private final ApplicationDao applicationDao;

    @Override
    @Transactional
    public boolean joinProject(String projectId, String profileId) {
        // project joining flow : two diff flows based on the project-joining visibility
        // if open -> directly add the user as a member
        // if private -> create an application and wait for the approval

        ProjectEntity project = returnProjectFromId(projectId);
        ProfileEntity profile = returnProfileFromId(profileId);

        if (project.getProjectJoiningVisibility().equals(ProjectJoiningStrategy.OPEN)) {
            ProjectMember newMember = ProjectMember.builder()
                    .project(project)
                    .profile(profile)
                    .role(ProjectRole.MEMBER)
                    .build();

            ProjectMember savedMember = projectMemberDao.save(newMember);
            project.getProjectMembers().add(savedMember);
            return true;
        } else if (project.getProjectJoiningVisibility().equals(ProjectJoiningStrategy.APPLICATION)) {
            Application application = Application.builder()
                    .project(project)
                    .status(ApplicationStatus.PENDING)
                    .build();

            Application savedApplication = applicationDao.save(application);

            project.getApplications().add(savedApplication);
            profile.getApplications().add(savedApplication);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void approveApplication(String applicationId) {
        Application application = returnApplicationFromId(applicationId);
        ProfileEntity profile = application.getProfile();
        ProjectEntity project = application.getProject();

        ProjectMember projectMember = ProjectMember.builder()
                .profile(profile)
                .project(project)
                .role(ProjectRole.MEMBER)
                .build();

        applicationDao.delete(application);
        ProjectMember savedProjectMember = projectMemberDao.save(projectMember);

        profile.getProjectMembers().add(savedProjectMember);
        project.getProjectMembers().add(savedProjectMember);
    }

    @Override
    @Transactional
    public void rejectApplication(String applicationId) {
        Application application = returnApplicationFromId(applicationId);

        ProjectEntity project = application.getProject();
        project.getApplications().remove(application);

        applicationDao.delete(application);
    }


    private ProjectEntity returnProjectFromId(String projectId) {
        return projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    private ProfileEntity returnProfileFromId(String profileId) {
        return profileDao.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    private Application returnApplicationFromId(String applicationId) {
        return applicationDao.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

}
