package com.roya.the_new_social_network.forum.posts.entities;

import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@DiscriminatorValue("DISCUSSION")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscussionPost {
    private String topic;
    private String content;
    private String parentId;
    private List<String> topPoints;
    private int upVotes;
    private int downVotes;
    private int replyCount;
    private boolean isPinned;

}
