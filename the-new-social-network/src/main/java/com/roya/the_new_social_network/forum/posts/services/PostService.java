package com.roya.the_new_social_network.forum.posts.services;

import com.roya.the_new_social_network.forum.posts.dto.requests.PamphletPostRequest;
import com.roya.the_new_social_network.forum.posts.dto.requests.BlogPostRequest;
import com.roya.the_new_social_network.forum.posts.dto.requests.DiscussionPostRequest;
import com.roya.the_new_social_network.forum.posts.dto.requests.MediaPostRequest;
import com.roya.the_new_social_network.forum.posts.dto.requests.TextPostRequest;
import com.roya.the_new_social_network.forum.posts.dto.responses.PostResponse;
import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;

import java.util.List;

public interface PostService {

    PostResponse createMediaPost(String userId, MediaPostRequest mediaPostRequest);

    PostResponse createMediaPostForProject(MediaPostRequest mediaPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId);

    PostResponse createTextPost(String userId, TextPostRequest textPostRequest);

    PostResponse createTextPostForProject(TextPostRequest textPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId);

    PostResponse createBlogPost(String userId, BlogPostRequest blogPostRequest);

    PostResponse createBlogPostForProject(BlogPostRequest blogPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId);

    PostResponse createDiscussionPost(String userId, DiscussionPostRequest discussionPostRequest);

    PostResponse createDiscussionPostForProject(DiscussionPostRequest discussionPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId);

    PostResponse createPamphletPost(PamphletPostRequest pamphletPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId);

    void editCaptionInPost(String postId, String newCaption, String userId);

    void changeUrlInPost(String postId, String newUrl, String userId);

    void editContentInTextPost(String postId, String newContent, String userId);

    void addOptionInDiscussionPost(String postId, String option, String userId);

    void removeOptionFromDiscussionPost(String optionId, String userId);

    // editBlogDetails in blog post

    Post getPost(String postId);

    List<Post> getPostsForProject(String projectId);

    List<Post> getPostsForUser(String userId);

    GlobalDeleteResponse deletePost(String postId, String userId);

}
