package com.roya.the_new_social_network.projects.applications;

import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDao applicationDao;
    private final ProjectDao projectDao;
    private final ProfileDao profileDao;
    private final ProjectMemberDao projectMemberDao;

    @Override
    @Transactional
    public String applyForProject(String profileId, String projectId) {
        ProfileEntity profile = profileDao.findById(profileId)
                .orElseThrow(() -> new RuntimeException(""));

        ProjectEntity project = projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException(""));

        Application application = Application.builder()
                .profile(profile)
                .project(project)
                .status(ApplicationStatus.PENDING)
                .build();

        Application savedApplication = applicationDao.save(application);
        project.getApplications().add(savedApplication); // notify the project's creator + admins for the new application

        ProjectMember applicant = ProjectMember.builder()
                .role(ProjectRole.APPLICANT)
                .profile(profile)
                .project(project)
                .build();

        projectMemberDao.save(applicant);

        return savedApplication.getApplicationId();
    }


}
