package com.roya.the_new_social_network.forum.posts.entities;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "VOTES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"optionId", "user_id"})
})
public class Vote {
    @Id
    private String voteId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "optionId", referencedColumnName = "optionId", nullable = false)
    private DiscussionOption option;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private ProfileEntity user;

    public Vote(){}

    public Vote(DiscussionOption option, ProfileEntity user) {
        this.voteId = option.getOptionId() + "_" + user.getProfileId();
        this.option = option;
        this.user = user;
    }

}
