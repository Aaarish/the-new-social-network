package com.roya.the_new_social_network.apprenticeship.services;

public interface ApprenticeshipService {
    void requestApprenticeship(String sourceProfileId, String targetProfileId);

    void removeApprenticeship(String apprenticeProfileId, String mentorProfileId);

//    void approveApprenticeship(String apprenticeId);

//    void rejectApprenticeship(String apprenticeId);

}
