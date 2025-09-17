package com.roya.the_new_social_network.projects.services;

import com.roya.the_new_social_network.global.access_management.projects.ProjectRole;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectWatchServiceImpl implements ProjectWatchService {
    private final ProjectMemberDao projectMemberDao;
    private final ProjectDao projectDao;

    // needs to create a background asynchronous worker that handles the recommendation algorithm

    @Override
    @Transactional
    public void watch(WatcherRequestDto watcherRequest) {
        ProjectMember watcher = ProjectMember.builder()
                .profile(watcherRequest.getProfile())
                .project(watcherRequest.getProject())
                .role(ProjectRole.WATCHER)
                .build();

        watcherRequest.getProject().getProjectMembers().add(watcher);
        projectMemberDao.save(watcher);
    }

    @Override
    @Transactional
    public void unWatch(String watcherId, WatcherRequestDto watcherRequest) {
        ProjectMember watcher = projectMemberDao.findById(watcherId)
                .orElseThrow(() -> new RuntimeException(""));

        watcherRequest.getProject().getProjectMembers().remove(watcher);
        projectMemberDao.delete(watcher);
    }
}
