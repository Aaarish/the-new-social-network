package com.roya.the_new_social_network.apprenticeship.services;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.ApprenticeDao;
import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.apprenticeship.MentorDao;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApprenticeshipServiceImpl implements ApprenticeshipService {
    private final ApprenticeDao apprenticeDao;
    private final MentorDao mentorDao;
    private final CommonDaoUtils utils;

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

            if (mentor.hasApprentice(sourceProfileId)) {
                return;
            }
        } else {
            mentor = new Mentor(targetProfile);
        }

        Apprentice apprentice = new Apprentice(sourceProfile, mentor);
        mentor.addApprentice(apprentice);
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

    @Override
    public boolean hasApprenticeship(String apprenticeProfileId, String mentorProfileId) {
        Mentor mentor = getMentorFromProfile(mentorProfileId);
        return mentor.hasApprentice(apprenticeProfileId);
    }

    @Override
    public List<Apprentice> getApprenticesOfMentor(String mentorProfileId) {
        Mentor mentor = getMentorFromProfile(mentorProfileId);
        return mentor.getApprentices();
    }

    @Override
    public List<Mentor> getMentorsOfApprentice(String apprenticeProfileId) {
        ProfileEntity profile = utils.returnProfileFromId(apprenticeProfileId);
        return profile.getMentors();
    }

    private Mentor getMentorFromProfile(String mentorProfileId) {
        ProfileEntity mentorProfile = utils.returnProfileFromId(mentorProfileId);

        return mentorDao.findByProfile(mentorProfile)
                .orElseThrow(() -> new IllegalArgumentException("Mentor not found"));
    }
}
