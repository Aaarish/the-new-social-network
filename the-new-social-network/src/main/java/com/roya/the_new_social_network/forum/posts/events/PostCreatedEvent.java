package com.roya.the_new_social_network.forum.posts.events;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class PostCreatedEvent extends ApplicationEvent {
    private final Post post;
    private final LocalDateTime eventTimestamp;

    public PostCreatedEvent(Object source, Post post) {
        super(source);
        this.post = post;
        this.eventTimestamp = LocalDateTime.now();
    }

}
