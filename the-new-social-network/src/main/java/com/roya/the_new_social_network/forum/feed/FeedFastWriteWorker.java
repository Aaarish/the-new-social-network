package com.roya.the_new_social_network.forum.feed;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.forum.posts.events.PostCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class FeedFastWriteWorker {

    @Async
    @EventListener
    @Transactional
    public void handlePostCreatedEvent(PostCreatedEvent event) {
        try {
            Set<FeedEntity> feeds = new HashSet<>();

            log.info("Handling PostCreatedEvent for post ID: {}", event.getPost().getPostId());
            Post post = event.getPost();

            boolean isProjectPost = post.getProject() != null;
            if (isProjectPost) {
                post.getProject().getProjectMembers().forEach(member -> feeds.add(member.getProfile().getFeed()));
            }

            post.getAuthor().getApprentices().forEach(apprentice -> feeds.add(apprentice.getProfile().getFeed()));

            int successCount = 0;
            for (FeedEntity feed : feeds) {
                feed.addPost(post);
                successCount++;
            }

            log.info("Successfully added post {} to {}/{} feeds", post.getPostId(), successCount, feeds.size());
        } catch (Exception e) {
            log.error("Error processing PostCreatedEvent for post ID {}: {}", event.getPost().getPostId(), e.getMessage());
        }
    }

}
