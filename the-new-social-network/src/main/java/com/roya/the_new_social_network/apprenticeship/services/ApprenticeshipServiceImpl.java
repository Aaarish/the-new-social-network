package com.roya.the_new_social_network.apprenticeship.services;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.ApprenticeDao;
import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.apprenticeship.MentorDao;
import com.roya.the_new_social_network.global.utils.CommonUtils;
import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApprenticeshipServiceImpl implements ApprenticeshipService{
    private final ProfileDao profileDao;
    private final ApprenticeDao apprenticeDao;
    private final MentorDao mentorDao;
    private final CommonUtils utils;

    @Override
    @Transactional
    public void requestApprenticeship(String sourceProfileId, String targetProfileId) {
        ProfileEntity sourceProfile = utils.returnProfileFromId(sourceProfileId);
        ProfileEntity targetProfile = utils.returnProfileFromId(targetProfileId);

        if (sourceProfileId.equals(targetProfileId)) {
            throw new IllegalArgumentException("A profile cannot apprentice to itself.");
        }

        Optional<Mentor> existingMentorOptional = mentorDao.findByProfile(targetProfile);

        Mentor mentor;
        if (existingMentorOptional.isPresent()) {
            mentor = existingMentorOptional.get();

            if (mentor.hasApprentice(sourceProfile)) {
                return;
            }
        } else {
            mentor = Mentor.builder()
                    .profile(targetProfile)
                    .build();

            mentor = mentorDao.save(mentor);
        }

        Apprentice apprentice = Apprentice.builder()
                .profile(sourceProfile)
                .mentor(mentor)
                .build();

        mentor.getApprentices().add(apprentice);
        mentorDao.save(mentor);
    }

    @Override
    @Transactional
    public void removeApprenticeship(String apprenticeProfileId, String mentorProfileId) {
        ProfileEntity apprenticeProfile = utils.returnProfileFromId(apprenticeProfileId);
        ProfileEntity mentorProfile = utils.returnProfileFromId(mentorProfileId);

        Mentor mentor = mentorDao.findByProfile(mentorProfile)
                .orElseThrow(() -> new IllegalArgumentException("Mentor not found"));

        Apprentice apprentice = apprenticeDao.findByProfileAndMentor(apprenticeProfile, mentor)
                .orElseThrow(() -> new IllegalArgumentException("Apprenticeship not found"));

        mentor.removeApprentice(apprentice);

        if (mentor.getApprenticeCount() == 0) {
            mentorDao.delete(mentor);
        } else {
            mentorDao.save(mentor);
        }
    }


    public boolean hasApprenticeship(ProfileEntity apprenticeProfile, Mentor mentor) {
        return mentor.getApprentices().stream()
                .anyMatch(apprentice -> apprentice.getProfile().equals(apprenticeProfile));
    }
}
