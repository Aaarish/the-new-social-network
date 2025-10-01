package com.roya.the_new_social_network.projects.services;

public interface ProjectJoiningService {
    boolean joinProject(String projectId, String profileId);

    void approveApplication(String applicationId);

    void rejectApplication(String applicationId);

}
