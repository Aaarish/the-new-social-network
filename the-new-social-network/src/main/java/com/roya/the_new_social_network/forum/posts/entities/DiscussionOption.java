package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "DISCUSSION_POST_OPTIONS")
public class DiscussionOption {
    @Id
    @Column(name = "option_id", nullable = false, unique = true)
    private String optionId;

    @Column(name = "option", nullable = false)
    private String option;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private DiscussionPost post;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Vote> votes;

    private int votesCount;

    public DiscussionOption(){}

    public DiscussionOption(int id, String option) {
        this.optionId = post.getPostId() + "_" + id;
        this.option = option;
    }

    public void upVote(ProfileEntity user) {
        this.votes.add(new Vote(this, user));
        this.votesCount = this.votes.size();
    }


    // Getters

    public String getOptionId() {
        return optionId;
    }

    public String getOption() {
        return option;
    }

    public int getVotesCount() {
        return votesCount;
    }
}
