package com.roya.the_new_social_network.global.utils;

import com.roya.the_new_social_network.forum.interactions.comments.Comment;
import com.roya.the_new_social_network.forum.interactions.comments.CommentDao;
import com.roya.the_new_social_network.forum.interactions.comments.CommentLike;
import com.roya.the_new_social_network.forum.interactions.comments.CommentLikeDao;
import com.roya.the_new_social_network.forum.posts.dao.PostDao;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.members.ProjectMemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonDaoUtils {
    private final ProjectDao projectDao;
    private final ProfileDao profileDao;
    private final ProjectMemberDao projectMemberDao;
    private final PostDao postDao;
    private final CommentDao commentDao;
    private final CommentLikeDao commentLikeDao;


    public ProfileEntity returnProfileFromId(String profileId) {
        return profileDao.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    public ProfileEntity returnProfileFromUsername(String username) {
        return profileDao.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    public ProjectEntity returnProjectFromId(String projectId) {
        return projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public ProjectMember returnProjectMemberFromId(String projectMemberId) {
        return projectMemberDao.findById(projectMemberId)
                .orElseThrow(() -> new IllegalArgumentException("Project member not found"));
    }

    public ProjectMember returnProjectMemberFromProfileIdAndProjectId(String profileId, String projectId) {
        return projectMemberDao.findByProfileAndProject(this.returnProfileFromId(profileId), this.returnProjectFromId(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project member not found"));
    }

    public Post returnPostFromId(String postId) {
        return postDao.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Comment returnCommentFromId(String commentId) {
        return commentDao.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
    }

    public CommentLike returnCommentLikeFromCommentIdAndUserId(String commentId, String userId) {

        return commentLikeDao.findByCommentAndUser(this.returnCommentFromId(commentId), this.returnProfileFromId(userId))
                .orElseThrow(() -> new IllegalStateException("Like not found for the given comment and user"));
    }

}
