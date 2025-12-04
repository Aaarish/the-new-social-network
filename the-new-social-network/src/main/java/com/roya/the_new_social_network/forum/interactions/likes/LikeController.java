package com.roya.the_new_social_network.forum.interactions.likes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> toggleLike(@PathVariable String postId, @AuthenticationPrincipal UserDetails userDetails) {
        likeService.toggleLike(postId, userDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}/like")
    public ResponseEntity<List<LikeResponse>> getPostLikes(@PathVariable String postId) {
        return ResponseEntity.ok(likeService.getLikesOfPost(postId));
    }

}
