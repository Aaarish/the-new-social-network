package com.roya.the_new_social_network.forum.posts;

import java.util.List;

public interface PostService {
    void createPost(Post post);
    void updatePost(String postId, Post post);
    Post getPost(String postId);
    List<Post> getPostsForProject(String projectId);
    List<Post> getPostsForProfile(String profileId);
    void deletePost(String postId);

}
