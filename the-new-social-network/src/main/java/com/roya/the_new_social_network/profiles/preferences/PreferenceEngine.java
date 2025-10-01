package com.roya.the_new_social_network.profiles.preferences;

import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.forum.posts.dao.PostDao;
import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PreferenceEngine {
    private final ProfileDao profileDao;
    private final PostDao postDao;

    // posts from subscribed-content (mentors + projects) : new-content :: (80:20)
    // step-1: fetch subscribed-content from past 1 day
    // step-2: if subscribed-content < 80%, then fetch older posts (past 2 days)
    // step-3: repeat #2 until the day for which content is fetched = 7 days
    // step-4: then get the whatever %age is made up by all the subscribed-content + rest of %age is to be fetched based on preferences

    public List<Post> fetchPostsForProfileFeed(String profileId) {
        ProfileEntity profile = profileDao.findById(profileId)
                .orElseThrow(() -> new RuntimeException("INVALID_PROFILE_ID : No profile exists with given profile_id"));

        List<Post> res = new ArrayList<>();


        for (Mentor mentor : profile.getMentors()) {
            // need to fetch posts from last 1 day that have public visibility
            res.addAll(postDao.getPostsFromLastDay(mentor.getProfile(), LocalDateTime.now()));
        }

        for (ProjectMember memberShip : profile.getProjectMembers()) {
            // need to fetch posts from last 1 day that have public visibility
            res.addAll(postDao.getProjectPostsFromLastDay(memberShip.getProject(), LocalDateTime.now()));
        }

        res.addAll(fetchByPreferences(profile.getPreferences())); // need to fetch posts from last 1 day that have public visibility

        return res;
    }

    private void rankPosts(List<Post> posts) {
        // needs to implement the logic for ranking
        // based on combination of [recency(create_date), popularity(likes_count + comments_count + reposts_count)]
    }

    private List<Post> fetchByPreferences(Set<Preference> preferences) {
        return postDao.getPreferredPostsForFeed(
                preferences.stream()
                        .map(pref -> pref.getCategory())
                        .collect(Collectors.toSet())
        );
    }
}
