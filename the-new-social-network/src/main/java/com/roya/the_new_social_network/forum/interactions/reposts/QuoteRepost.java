package com.roya.the_new_social_network.forum.interactions.reposts;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;

@DiscriminatorValue("QUOTE_REPOST")
@Getter
public class QuoteRepost extends Repost {
    private String quote;

    public QuoteRepost(Post post, ProfileEntity user, String quote) {
        super(post, user, ShareType.QUOTE);
        this.quote = quote;
    }

    public void editRepostQuote(String quote) {
        this.quote = quote;
    }

    public void clearQuote() {
        this.quote = null;
        super.setShareTypeToRepost();
    }

}
