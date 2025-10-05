package com.roya.the_new_social_network.projects.services.impl;

import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import com.roya.the_new_social_network.projects.members.ProjectRole;
import com.roya.the_new_social_network.projects.services.ProjectWatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProjectWatchServiceImpl implements ProjectWatchService {
    private final ProjectMemberDao projectMemberDao;
    private final ProfileDao profileDao;
    private final ProjectDao projectDao;

    // needs to create a background asynchronous worker that handles the recommendation algorithm

    @Override
    @Transactional
    public void watch(String profileId, String projectId) {
        ProfileEntity profile = returnProfileFromId(profileId);
        ProjectEntity project = returnProjectFromId(projectId);

        ProjectMember watcher = ProjectMember.builder()
                .profile(profile)
                .project(project)
                .role(ProjectRole.WATCHER)
                .build();

        ProjectMember savedWatcher = projectMemberDao.save(watcher);
        project.getProjectMembers().add(savedWatcher);
    }

    @Override
    @Transactional
    public void unWatch(String profileId, String projectId) {
        ProfileEntity profile = returnProfileFromId(profileId);
        ProjectEntity project = returnProjectFromId(projectId);


        projectMemberDao.findByProfileAndProject(profile, project)
                .ifPresent(watcher -> {
                    project.getProjectMembers().remove(watcher);
                    projectMemberDao.delete(watcher);
                });
    }

    private ProjectEntity returnProjectFromId(String projectId) {
        return projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException("profile not found"));
    }

    private ProfileEntity returnProfileFromId(String profileId) {
        return profileDao.findById(profileId)
                .orElseThrow(() -> new RuntimeException("profile not found"));
    }
}
