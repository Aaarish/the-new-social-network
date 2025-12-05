package com.roya.the_new_social_network.forum.media;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaUploadResponse {
    private String mediaId;
    private String uploadUrl;

}
