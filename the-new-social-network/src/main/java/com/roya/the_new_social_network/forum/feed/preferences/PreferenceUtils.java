package com.roya.the_new_social_network.forum.feed.preferences;

import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PreferenceUtils implements PreferenceService {
    private final CommonDaoUtils daoUtils;


    @Override
    public void setPreferenceForUser(PreferenceRequest request, String userId) {
        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        Map<PreferenceCategory, Integer> map = user.getPreferencesMap();
        map.put(PreferenceCategory.valueOf(request.getCategory()), request.getLevel());
    }

    @Override
    public void removePreferenceFromUser(PreferenceRequest request, String userId) {
        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        PreferenceCategory prefCategory = PreferenceCategory.valueOf(request.getCategory());
        if (user.getPreferencesMap() != null) user.getPreferencesMap().remove(prefCategory);
    }

    @Override
    public Map<PreferenceCategory, Integer> getUserPreferences(String userId) {
        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        return user.getPreferencesMap();
    }


    List<PreferenceCategory> getListOfPreferences() {
        return List.of(PreferenceCategory.values());
    }

}
