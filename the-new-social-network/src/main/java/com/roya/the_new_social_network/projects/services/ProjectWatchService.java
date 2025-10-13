package com.roya.the_new_social_network.projects.services;


import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;

import java.util.List;

public interface ProjectWatchService {
    void watch(String profileId, String projectId);

    void unWatch(String profileId, String projectId);

    boolean isWatching(String profileId, String projectId);

    List<ProjectEntity> getWatchedProjects(String profileId);

    List<ProfileEntity> getWatchers(String projectId);

    int getWatcherCount(String projectId);

}
