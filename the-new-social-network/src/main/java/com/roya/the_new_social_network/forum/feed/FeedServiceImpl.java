package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.apprenticeship.MentorDao;
import com.roya.the_new_social_network.forum.posts.dao.PostDao;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.utils.CommonUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.profiles.preferences.Preference;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectJoiningStrategy;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final PostDao postDao;
    private final ProjectDao projectDao;
    private final MentorDao mentorDao;
    private final CommonUtils utils;

    @Override
    public FeedEntity loadFeed(String profileId) {
        // TODO : optimize this method to fetch less posts and with a single query instead of multiple queries + in-memory filtering

//        ProfileEntity profile = utils.returnProfileFromId(profileId);
//
//        Set<Preference> preferences = profile.getPreferences();
//        List<ProjectMember> projectMembers = profile.getProjectMembers();
//        List<Mentor> mentors = profile.getMentors();
//
//        for (ProjectMember pm : projectMembers) {
//            feedPosts.addAll(pm.getProject().getPosts());
//        }
//
//        for (Mentor mentor : mentors) {
//            feedPosts.addAll(mentor.getProfile().getPosts());
//            feedPosts.addAll(mentor.getProfile().getProjectMembers().stream()
//                    .filter(pm -> pm.getProject().getProjectJoiningVisibility().equals(ProjectJoiningStrategy.OPEN))
//                    .flatMap(pm -> pm.getProject().getPosts().stream()).toList());
//        }
//
//        for (Preference preference : preferences) {
//            feedPosts.addAll(postDao.findByCategory(preference.getCategory()));
//        }

        List<Post> feedPosts = postDao.findFeedPostsForProfile(
                profileId,
                15,  // posts from user's projects
                10,  // posts from mentors
                10,  // posts from mentor's open projects
                15   // posts from user's preference categories
        );

        return new FeedEntity(feedPosts);
    }

}
