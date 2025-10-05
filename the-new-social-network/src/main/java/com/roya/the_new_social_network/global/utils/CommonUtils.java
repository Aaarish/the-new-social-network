package com.roya.the_new_social_network.global.utils;

import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonUtils {
    private final ProjectDao projectDao;
    private final ProfileDao profileDao;
    private final ProjectMemberDao projectMemberDao;


    public ProfileEntity returnProfileFromId(String profileId) {
        return profileDao.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    public ProjectEntity returnProjectFromId(String projectId) {
        return projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public ProjectMember returnProjectMemberFromId(String projectMemberId) {
        return projectMemberDao.findById(projectMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Project member not found"));
    }

    public ProjectMember returnProjectMemberFromProfileIdAndProjectId(String profileId, String projectId) {
        return projectMemberDao.findByProfileAndProject(this.returnProfileFromId(profileId),
                this.returnProjectFromId(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project member not found"));
    }

}
