package com.roya.the_new_social_network.forum.interactions.reposts;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface RepostService {

    RepostResponse repost(String postId, UserDetails userDetails);

    RepostResponse repostWithQuote(String postId, String quote, UserDetails userDetails);

    GlobalDeleteResponse unRepost(String postId, UserDetails userDetails);

    List<RepostResponse> getRepostsOfUser(String userId);

    int getRepostsCountForPost(String postId);

}
