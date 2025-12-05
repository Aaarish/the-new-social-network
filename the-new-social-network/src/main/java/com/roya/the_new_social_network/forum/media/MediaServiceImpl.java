package com.roya.the_new_social_network.forum.media;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaDao mediaDao;
    private final CommonDaoUtils daoUtils;

    @Override
    public Media uploadMediaMetadata(MediaUploadRequest request, UserDetails userDetails) {
        ProfileEntity user = daoUtils.returnProfileFromUsername(userDetails.getUsername());

        Media media = new Media(
                request.getFilename(),
                request.getFileSizeInBytes(),
                user,
                request.getMediaType(),
                request.getMimeType(),
                request.getSource(),
                request.getVisibility()
        );

        return mediaDao.save(media);
    }

    @Override
    public void processUploadedMediaForPost(String postId, String mediaId) {
        Media media = getMediaById(mediaId);
        Post post = daoUtils.returnPostFromId(postId);

        media.linkToPost(post);
        media.setPathUrl("/" + media.getSource() + "/" + media.getMediaId());

        mediaDao.save(media);
    }

    public Media getMediaById(String mediaId) {
        return mediaDao.findById(mediaId)
                .orElseThrow(() -> new IllegalArgumentException("Media not found"));
    }
}
