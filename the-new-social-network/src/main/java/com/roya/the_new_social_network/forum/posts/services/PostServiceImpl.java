package com.roya.the_new_social_network.forum.posts.services;

import com.roya.the_new_social_network.forum.posts.dao.DiscussionOptionDao;
import com.roya.the_new_social_network.forum.posts.dto.requests.*;
import com.roya.the_new_social_network.forum.posts.dto.responses.PostResponse;
import com.roya.the_new_social_network.forum.posts.entities.*;
import com.roya.the_new_social_network.forum.posts.dao.PostDao;
import com.roya.the_new_social_network.forum.posts.enums.PostVisibility;
import com.roya.the_new_social_network.forum.posts.enums.ProjectPostLabel;
import com.roya.the_new_social_network.forum.posts.events.PostCreatedEvent;
import com.roya.the_new_social_network.global.access.GlobalAccessValidationService;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final DiscussionOptionDao optionDao;
    private final CommonDaoUtils daoUtils;
    private final GlobalAccessValidationService accessValidationService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public PostResponse createMediaPost(String userId, MediaPostRequest mediaPostRequest) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);

        MediaPost mediaPost = new MediaPost(author, mediaPostRequest.getMediaUrl(), mediaPostRequest.getMedia(), mediaPostRequest.getCaption());
        MediaPost savedPost = postDao.save(mediaPost);

//        mongoTemplate.dump(post); needs to dump the post in the mongodb collection to store it without any index or whatever

        log.info("media post created for user {} with post_id: {}", savedPost.getAuthor().getProfileId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createMediaPostForProject(MediaPostRequest mediaPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);
        ProjectEntity project = daoUtils.returnProjectFromId(projectId);

        MediaPost mediaPost = new MediaPost(author, project, postVisibility, postLabel, mediaPostRequest.getMediaUrl(), mediaPostRequest.getMedia(), mediaPostRequest.getCaption());
        MediaPost savedPost = postDao.save(mediaPost);

        log.info("media post created for project {} with post_id: {}", savedPost.getProject().getProjectId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createTextPost(String userId, TextPostRequest textPostRequest) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);

        TextPost textPost = new TextPost(author, textPostRequest.getContent());
        TextPost savedPost = postDao.save(textPost);

        log.info("text post created for user {} with post_id: {}", savedPost.getAuthor().getProfileId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createTextPostForProject(TextPostRequest textPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);
        ProjectEntity project = daoUtils.returnProjectFromId(projectId);

        TextPost textPost = new TextPost(author, project, postVisibility, postLabel, textPostRequest.getContent());
        TextPost savedPost = postDao.save(textPost);

        log.info("text post created for project {} with post_id: {}", savedPost.getProject().getProjectId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createBlogPost(String userId, BlogPostRequest blogPostRequest) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);

        BlogPost blogPost = new BlogPost(author, blogPostRequest.getTitle(), blogPostRequest.getCategory(), blogPostRequest.getContent(), blogPostRequest.getReadTime(), blogPostRequest.getMedia());
        BlogPost savedPost = postDao.save(blogPost);

        log.info("blog post created for user {} with post_id: {}", savedPost.getAuthor().getProfileId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createBlogPostForProject(BlogPostRequest blogPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);
        ProjectEntity project = daoUtils.returnProjectFromId(projectId);

        BlogPost blogPost = new BlogPost(author, project, postVisibility, postLabel, blogPostRequest.getTitle(), blogPostRequest.getCategory(), blogPostRequest.getContent(), blogPostRequest.getReadTime(), blogPostRequest.getMedia());
        BlogPost savedPost = postDao.save(blogPost);

        log.info("blog post created for project {} with post_id: {}", savedPost.getProject().getProjectId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createDiscussionPost(String userId, DiscussionPostRequest discussionPostRequest) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);

        DiscussionPost discussionPost = new DiscussionPost(author, discussionPostRequest.getTopic(), discussionPostRequest.getContent(), new ArrayList<>());

        for (String option : discussionPostRequest.getOptions()) {
            if (discussionPostRequest.getOptions().size() > 5) {
                throw new IllegalArgumentException("Cannot have more than 5 options in a discussion post");
            }

            discussionPost.addOption(option);
        }

        DiscussionPost savedPost = postDao.save(discussionPost);

        log.info("discussion post created for user {} with post_id: {}", savedPost.getAuthor().getProfileId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createDiscussionPostForProject(DiscussionPostRequest discussionPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);
        ProjectEntity project = daoUtils.returnProjectFromId(projectId);

        DiscussionPost discussionPost = new DiscussionPost(author, project, postVisibility, postLabel, discussionPostRequest.getTopic(), discussionPostRequest.getContent(), new ArrayList<>());

        for (String option : discussionPostRequest.getOptions()) {
            if (discussionPostRequest.getOptions().size() > 5) {
                throw new IllegalArgumentException("Cannot have more than 5 options in a discussion post");
            }

            discussionPost.addOption(option);
        }

        DiscussionPost savedPost = postDao.save(discussionPost);

        log.info("discussion post created for project {} with post_id: {}", savedPost.getProject().getProjectId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public PostResponse createPamphletPost(PamphletPostRequest pamphletPostRequest, String projectId, PostVisibility postVisibility, ProjectPostLabel postLabel, String userId) {
        ProfileEntity author = daoUtils.returnProfileFromId(userId);
        ProjectEntity project = daoUtils.returnProjectFromId(projectId);

        PamphletPost mediaPost = new PamphletPost(author, project, postVisibility, postLabel, pamphletPostRequest.getMediaUrl(), pamphletPostRequest.getMedia(), pamphletPostRequest.getCaption());
        PamphletPost savedPost = postDao.save(mediaPost);

        log.info("pamphlet post created for project {} with post_id: {}", savedPost.getProject().getProjectId(), savedPost.getPostId());
        sendPostCreatedEvent(savedPost);

        return PostResponse.fromEntity(savedPost);
    }

    @Transactional
    @Override
    public void editCaptionInPost(String postId, String newCaption, String userId) {
        MediaPost post = postDao.findByIdAndPostTypeAsMedia(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found or not a media post"));

        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        accessValidationService.validateAccessToPost(user, post);

        post.editCaption(newCaption);
        postDao.save(post);
    }

    @Transactional
    @Override
    public void changeUrlInPost(String postId, String newUrl, String userId) {
        MediaPost post = postDao.findByIdAndPostTypeAsMedia(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found or not a media post"));

        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        accessValidationService.validateAccessToPost(user, post);

        post.changeMediaUrl(newUrl);
        postDao.save(post);
    }

    @Transactional
    @Override
    public void editContentInTextPost(String postId, String newContent, String userId) {
        TextPost post = postDao.findByIdAndPostTypeAsText(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found or not a text post"));

        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        accessValidationService.validateAccessToPost(user, post);

        post.editContent(newContent);
        postDao.save(post);
    }

    @Transactional
    @Override
    public void addOptionInDiscussionPost(String postId, String option, String userId) {
        DiscussionPost post = postDao.findByIdAndPostTypeAsDiscussion(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found or not a discussion post"));

        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        accessValidationService.validateAccessToPost(user, post);

        post.addOption(option);
        postDao.save(post);
    }

    @Transactional
    @Override
    public void removeOptionFromDiscussionPost(String optionId, String userId) {
        DiscussionPost post = postDao.findByIdAndPostTypeAsDiscussion(optionId.split("_")[0])
                .orElseThrow(() -> new IllegalArgumentException("Post not found or not a discussion post"));

        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        accessValidationService.validateAccessToPost(user, post);

        DiscussionOption option = optionDao.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Option not found"));

        post.getOptions().remove(option);
        postDao.save(post);
    }

    @Override
    public Post getPost(String postId) {
        return daoUtils.returnPostFromId(postId);
    }

    @Override
    public List<Post> getPostsForProject(String projectId) {
        ProjectEntity project = daoUtils.returnProjectFromId(projectId);
        return project.getPosts();
    }

    @Override
    public List<Post> getPostsForUser(String userId) {
        ProfileEntity user = daoUtils.returnProfileFromId(userId);
        return user.getPosts();
    }

    @Transactional
    @Override
    public GlobalDeleteResponse deletePost(String postId, String userId) {
        Post post = daoUtils.returnPostFromId(postId);
        ProfileEntity user = daoUtils.returnProfileFromId(userId);

        accessValidationService.validateAccessToPost(user, post);
        postDao.delete(post);

        return GlobalDeleteResponse.builder()
                .success(true)
                .resourceId(postId)
                .message("Post deleted successfully")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    private void sendPostCreatedEvent(Post post) {
        log.info("post-created-event sent for post_id: {}", post.getPostId());
        eventPublisher.publishEvent(new PostCreatedEvent(this, post));
    }
}
