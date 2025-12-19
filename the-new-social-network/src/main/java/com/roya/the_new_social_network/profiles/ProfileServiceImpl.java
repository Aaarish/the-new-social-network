package com.roya.the_new_social_network.profiles;

import com.roya.the_new_social_network.apprenticeship.api.dto.response.ApprenticeInfo;
import com.roya.the_new_social_network.apprenticeship.api.dto.response.MentorInfo;
import com.roya.the_new_social_network.profiles.api.dto.request.UpdateProfileRequest;
import com.roya.the_new_social_network.profiles.api.dto.response.*;
import com.roya.the_new_social_network.profiles.preferences.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;

    @Override
    public ProfileEntity createProfile(ProfileEntity profile) {
        return profileDao.save(profile);
    }

    @Override
    public ProfileEntity getProfile(String profileId) {
        return returnProfileFromId(profileId);
    }

    @Override
    public ProfileProjectsResponse getProjectsForProfile(String profileId) {
        ProfileEntity profile = returnProfileFromId(profileId);

        return ProfileProjectsResponse.builder()
                .profileId(profile.getProfileId())
                .username(profile.getUsername())
                .projects(profile.getProjectMembers().stream()
                        .map(pm -> ProjectMembershipInfo.builder()
                                .projectId(pm.getProject().getProjectId())
                                .projectTitle(pm.getProject().getTitle())
                                .role(pm.getRole().toString())
                                .joinedAt(pm.getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .totalProjects(profile.getProjectMembershipCount())
                .build();
    }

    @Override
    public ProfileCreatedProjectsResponse getCreatedProjectsForProfile(String profileId) {
        ProfileEntity profile = returnProfileFromId(profileId);

        return ProfileCreatedProjectsResponse.builder()
                .profileId(profile.getProfileId())
                .username(profile.getUsername())
                .createdProjects(profile.getProjectMembers().stream()
                        .filter(pm -> pm.getRole().toString().equals("OWNER"))
                        .map(pm -> CreatedProjectInfo.builder()
                                .projectId(pm.getProject().getProjectId())
                                .projectTitle(pm.getProject().getTitle())
                                .projectDescription(pm.getProject().getDescription())
                                .memberCount(pm.getProject().getProjectMembers().size())
                                .createdAt(pm.getProject().getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .totalCreatedProjects(profile.getProjectMembers().stream()
                        .filter(pm -> pm.getRole().toString().equals("OWNER"))
                        .count())
                .build();
    }

    @Override
    public ProfileApprenticesResponse getApprenticesOfProfile(String profileId) {
        ProfileEntity profile = returnProfileFromId(profileId);

        return ProfileApprenticesResponse.builder()
                .profileId(profile.getProfileId())
                .username(profile.getUsername())
                .apprentices(profile.getApprentices().stream()
                        .map(a -> ApprenticeInfo.builder()
                                .apprenticeId(a.getApprenticeId())
                                .profileId(a.getProfile().getProfileId())
                                .name(a.getProfile().getName())
                                .username(a.getProfile().getUsername())
                                .since(a.getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .totalApprentices(profile.getApprenticeCount())
                .build();
    }

    @Override
    public ProfileMentorsResponse getMentorsOfProfile(String profileId) {
        ProfileEntity profile = returnProfileFromId(profileId);

        return ProfileMentorsResponse.builder()
                .profileId(profile.getProfileId())
                .username(profile.getUsername())
                .mentors(profile.getMentors().stream()
                        .map(m -> MentorInfo.builder()
                                .mentorId(m.getMentorId())
                                .profileId(m.getProfile().getProfileId())
                                .name(m.getProfile().getName())
                                .username(m.getProfile().getUsername())
                                .apprenticeCount(m.getApprenticeCount())
                                .since(m.getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .totalMentors(profile.getMentorCount())
                .build();
    }

    @Override
    public void deleteProfile(String profileId) {
        ProfileEntity profile = returnProfileFromId(profileId);
        profileDao.delete(profile);
    }

    @Override
    @Transactional
    public ProfileEntity updateProfile(String profileId, UpdateProfileRequest request) {
        ProfileEntity profile = returnProfileFromId(profileId);

        if (request.getName() != null) profile.setName(request.getName());
        if (request.getContact() != null) profile.setContact(request.getContact());
        if (request.getBio() != null) profile.setBio(request.getBio());

        return profileDao.save(profile);
    }

    @Override
    public List<ProfileEntity> searchProfiles(String query) {
        return null;
    }

    @Override
    public ProfileEntity getProfileByUsername(String username) {
        return profileDao.findByUsername(username)
                .orElseThrow(()-> new IllegalStateException("Profile with username " + username + " does not exist"));
    }

    @Override
    public List<Preference> getProfilePreferences(String profileId) {
        return null;
    }

//    @Override
//    @Transactional
//    public void addPreference(String profileId, PreferenceRequest prefRequest) {
//        ProfileEntity profile = returnProfileFromId(profileId);
//
//        Preference preference = Preference.builder()
//                .category(PreferenceCategory.valueOf(prefRequest.getCategory()))
//                .profile(profile)
//                .build();
//
//        if (prefRequest.getInterestLevel() != null) preference.setInterestLevel(prefRequest.getInterestLevel());
//        if (prefRequest.getDescription() != null) preference.setDescription(prefRequest.getDescription());
//
//        profile.getPreferences().add(preference);
//    }

//    @Override
//    @Transactional
//    public void removePreference(String profileId, String preferenceId) {
//        ProfileEntity profile = returnProfileFromId(profileId);
//
//        for (Preference pref : profile.getPreferences()) {
//            if (pref.getPrefId().toString().equals(preferenceId)) {
//                profile.getPreferences().remove(pref);
//                break;
//            }
//        }
//    }

    private ProfileEntity returnProfileFromId(String profileId) {
        return profileDao.findById(profileId)
                .orElseThrow(()-> new IllegalStateException("Profile does not exist"));
    }
}
