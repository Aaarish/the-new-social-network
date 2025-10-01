package com.roya.the_new_social_network.forum.posts.services;

import com.roya.the_new_social_network.forum.posts.entities.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    void updatePost(String postId, Post post);
    Post getPost(String postId);
    List<Post> getPostsForProject(String projectId);
    List<Post> getPostsForProfile(String profileId);
    void deletePost(String postId);

}
