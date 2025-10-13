package com.roya.the_new_social_network.profiles;

import com.roya.the_new_social_network.profiles.api.dto.ProfileRequest;
import com.roya.the_new_social_network.profiles.api.dto.request.PreferenceRequest;
import com.roya.the_new_social_network.profiles.api.dto.request.UpdateProfileRequest;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileApprenticesResponse;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileCreatedProjectsResponse;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileMentorsResponse;
import com.roya.the_new_social_network.profiles.api.dto.response.ProfileProjectsResponse;
import com.roya.the_new_social_network.profiles.preferences.Preference;

import java.util.List;

public interface ProfileService {
    ProfileEntity createProfile(ProfileEntity profile);

    ProfileEntity getProfile(String profileId);

    ProfileProjectsResponse getProjectsForProfile(String profileId);

    ProfileCreatedProjectsResponse getCreatedProjectsForProfile(String profileId);

    ProfileApprenticesResponse getApprenticesOfProfile(String profileId);

    ProfileMentorsResponse getMentorsOfProfile(String profileId);

    void deleteProfile(String profileId);

    ProfileEntity updateProfile(String profileId, UpdateProfileRequest request);

    List<ProfileEntity> searchProfiles(String query);

    ProfileEntity getProfileByUsername(String username);

    List<Preference> getProfilePreferences(String profileId);

    void addPreference(String profileId, PreferenceRequest prefRequest);

    void removePreference(String profileId, String preferenceId);

//    List<Post> getProfilePosts(String profileId, int page, int size);

//    ProfileStatsDto getProfileStats(String profileId);

}
