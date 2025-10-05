package com.roya.the_new_social_network.projects.services;


public interface ProjectWatchService {
    void watch(String profileId, String projectId);
    void unWatch(String profileId, String projectId);

}
