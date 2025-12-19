package com.roya.the_new_social_network.forum.feed.preferences;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;

import java.util.Map;

public interface PreferenceService {

    void setPreferenceForUser(PreferenceRequest request, String userId);

    void removePreferenceFromUser(PreferenceRequest request, String userId);

    Map<PreferenceCategory, Integer> getUserPreferences(String userId);

}
