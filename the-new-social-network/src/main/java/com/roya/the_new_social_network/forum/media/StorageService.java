package com.roya.the_new_social_network.forum.media;

public interface StorageService {

    String generateMediaUploadUrl(String mediaId);

    String generateMediaDownloadUrl(String mediaId);

}
