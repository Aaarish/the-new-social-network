package com.roya.the_new_social_network.forum.media;

import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final StorageService storageService;


    @PostMapping
    public ResponseEntity<MediaUploadResponse> uploadMedia(@RequestBody MediaUploadRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        Media media = mediaService.uploadMediaMetadata(request, userDetails);
        String url = storageService.generateMediaUploadUrl(media.getMediaId());

        MediaUploadResponse uploadResponse = MediaUploadResponse.builder()
                .mediaId(media.getMediaId())
                .uploadUrl(url)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(uploadResponse);
    }

    @PutMapping("/post/{postId}/callback")
    public ResponseEntity<Void> callbackMediaUploadForPost(@PathVariable("postId") String postId, @RequestParam String mediaId) {
        // Handle post-upload processing if needed
        mediaService.processUploadedMediaForPost(postId, mediaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<MediaUploadResponse> downloadMedia(@PathVariable String mediaId) {
        String url = storageService.generateMediaDownloadUrl(mediaId);

        MediaUploadResponse uploadResponse = MediaUploadResponse.builder()
                .mediaId(mediaId)
                .uploadUrl(url)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(uploadResponse);
    }

}
