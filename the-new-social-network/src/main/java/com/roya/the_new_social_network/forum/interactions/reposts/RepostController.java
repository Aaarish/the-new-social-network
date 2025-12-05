package com.roya.the_new_social_network.forum.interactions.reposts;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RepostController {
    private final RepostService repostService;

    @PostMapping("/posts/{postId}/repost")
    public ResponseEntity<RepostResponse> repost(String postId,
                                                          @RequestParam(required = false) boolean hasQuote,
                                                          @RequestBody RepostRequest request,
                                                          @AuthenticationPrincipal UserDetails userDetails) {

        RepostResponse response;
        if (hasQuote) {
            response = repostService.repostWithQuote(postId, request.getQuote(), userDetails);
        } else {
            response = repostService.repost(postId, userDetails);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/posts/{postId}/repost")
    public ResponseEntity<GlobalDeleteResponse> unRepost(String postId, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(repostService.unRepost(postId, userDetails));
    }

    @GetMapping("/posts/{postId}/repost")
    public ResponseEntity<Integer> getRepostsCountForPost(String postId) {
        return ResponseEntity.ok(repostService.getRepostsCountForPost(postId));
    }

    @GetMapping("/users/{userId}/repost")
    public ResponseEntity<List<RepostResponse>> getRepostsOfUser(String userId) {
        return ResponseEntity.ok(repostService.getRepostsOfUser(userId));
    }

}
