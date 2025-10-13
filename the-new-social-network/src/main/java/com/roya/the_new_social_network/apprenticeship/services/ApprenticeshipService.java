package com.roya.the_new_social_network.apprenticeship.services;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.Mentor;

import java.util.List;

public interface ApprenticeshipService {
    void requestApprenticeship(String sourceProfileId, String targetProfileId);

    void removeApprenticeship(String apprenticeProfileId, String mentorProfileId);

    boolean hasApprenticeship(String apprenticeProfileId, String mentorProfileId);

    List<Apprentice> getApprenticesOfMentor(String mentorProfileId);

    List<Mentor> getMentorsOfApprentice(String apprenticeProfileId);

//    Object getApprenticeshipStats(String profileId);

//    void approveApprenticeship(String apprenticeId);

//    void rejectApprenticeship(String apprenticeId);

}
