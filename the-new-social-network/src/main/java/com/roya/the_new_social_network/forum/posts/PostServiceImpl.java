package com.roya.the_new_social_network.forum.posts;

import com.roya.the_new_social_network.profiles.ProfileDao;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectDao;
import com.roya.the_new_social_network.projects.ProjectEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final ProjectDao projectDao;
    private final ProfileDao profileDao;

    @Override
    public void createPost(Post post) {
        Post savedPost = postDao.save(post);


//        mongoTemplate.dump(post); needs to dump the post in the mongodb collection to store it without any index or whatever
    }

    @Override
    public void updatePost(String postId, Post post) {

    }

    @Override
    public Post getPost(String postId) {
        return postDao.findById(postId)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public List<Post> getPostsForProject(String projectId) {
        ProjectEntity project = projectDao.findById(projectId)
                .orElseThrow(() -> new RuntimeException(""));

        return project.getPosts();
    }

    @Override
    public List<Post> getPostsForProfile(String profileId) {
        ProfileEntity profile = profileDao.findById(profileId)
                .orElseThrow(() -> new RuntimeException(""));

        return profile.getPosts();

    }

    @Override
    public void deletePost(String postId) {
        Post post = postDao.findById(postId)
                .orElseThrow(() -> new RuntimeException(""));

        postDao.delete(post);
    }
}
