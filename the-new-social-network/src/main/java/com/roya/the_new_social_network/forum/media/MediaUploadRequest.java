package com.roya.the_new_social_network.forum.media;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaUploadRequest {
    private String filename;
    private int fileSizeInBytes;
    private MediaType mediaType;
    private String mimeType;
    private String source;
    private String visibility;

}
