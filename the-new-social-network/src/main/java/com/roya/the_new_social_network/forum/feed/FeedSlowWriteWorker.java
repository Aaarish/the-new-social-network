package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.apprenticeship.MentorDao;
import com.roya.the_new_social_network.forum.posts.dao.PostDao;
import com.roya.the_new_social_network.global.enums.PreferenceCategory;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FeedSlowWriteWorker implements FeedService {
    private final PostDao postDao;
    private final ProjectDao projectDao;
    private final MentorDao mentorDao;
    private final CommonDaoUtils daoUtils;

    @Override
    public FeedEntity loadFeed(String profileId) {
        // TODO : optimize this method to fetch less posts and with a single query instead of multiple queries + in-memory filtering

        ProfileEntity profile = daoUtils.returnProfileFromId(profileId);
        FeedEntity feed = profile.getFeed();
        Map<PreferenceCategory, Integer> preferencesMap = profile.getPreferencesMap();

        if (feed.isFeedStale()) {
            for (PreferenceCategory pref : preferencesMap.keySet()) {

            }
        }

        return null;
    }

}
