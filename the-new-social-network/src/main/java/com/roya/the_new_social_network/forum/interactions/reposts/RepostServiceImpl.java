package com.roya.the_new_social_network.forum.interactions.reposts;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RepostServiceImpl implements RepostService {
    private final RepostDao repostDao;
    private final CommonDaoUtils daoUtils;

    @Transactional
    @Override
    public RepostResponse repost(String postId, UserDetails userDetails) {
        Post post = daoUtils.returnPostFromId(postId);
        ProfileEntity user = daoUtils.returnProfileFromUsername(userDetails.getUsername());

        Repost repost = new Repost(post, user, Repost.ShareType.REPOST);
        log.info("Reposted post {} by user {}", postId, user.getProfileId());

        return RepostResponse.fromEntity(repost);
    }

    @Transactional
    @Override
    public RepostResponse repostWithQuote(String postId, String quote, UserDetails userDetails) {
        Post post = daoUtils.returnPostFromId(postId);
        ProfileEntity user = daoUtils.returnProfileFromUsername(userDetails.getUsername());

        Repost repost = new Repost(post, user, Repost.ShareType.QUOTE);
        log.info("Reposted post {} by user {}", postId, user.getProfileId());

        return RepostResponse.fromEntity(repost);
    }

    @Transactional
    @Override
    public GlobalDeleteResponse unRepost(String postId, UserDetails userDetails) {
        ProfileEntity user = daoUtils.returnProfileFromUsername(userDetails.getUsername());
        String repostId = postId + "_" + user.getProfileId();

        Repost repost = repostDao.findById(repostId)
                .orElseThrow(() -> new IllegalArgumentException("Repost not found"));

        repostDao.delete(repost);
        log.info("Repost {} removed by user {}", postId, user.getProfileId());

        return GlobalDeleteResponse.builder()
                .success(true)
                .resourceId(repostId)
                .message("Repost successfully removed")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public List<RepostResponse> getRepostsOfUser(String userId) {
        ProfileEntity user = daoUtils.returnProfileFromId(userId);

        return repostDao.findAllByUser(user).stream()
                .map(RepostResponse::fromEntity)
                .toList();
    }

    @Override
    public int getRepostsCountForPost(String postId) {
        Post post = daoUtils.returnPostFromId(postId);

        return post.getReposts().size();
    }
}
