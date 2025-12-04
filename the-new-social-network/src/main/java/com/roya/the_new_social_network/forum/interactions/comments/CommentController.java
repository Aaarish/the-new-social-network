package com.roya.the_new_social_network.forum.interactions.comments;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.utils.CommonDaoUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommonDaoUtils utils;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> addComment(@PathVariable String postId,
                                                      @Valid @RequestBody CommentRequest request,
                                                      @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(request.getContent(), postId, userDetails));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getPostComments(@PathVariable String postId,
                                                                 @RequestParam(required = false, defaultValue = "false") boolean includeReplies) {

        return ResponseEntity.ok(commentService.getCommentsOfPost(postId, includeReplies));
    }


    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> editComment(@PathVariable String commentId,
                                                       @Valid @RequestBody CommentRequest request,
                                                       @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(commentService.editComment(commentId, request.getContent(), userDetails));
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<GlobalDeleteResponse> deleteComment(@PathVariable String commentId,
                                                              @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(commentService.deleteComment(commentId, userDetails));
    }

    @PostMapping("/comments/{commentId}/replies")
    public ResponseEntity<CommentResponse> addReply(@PathVariable String commentId,
                                                    @Valid @RequestBody CommentRequest request,
                                                    @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addReply(commentId, request.getContent(), userDetails));
    }

    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<Void> likeComment(@PathVariable String commentId,
                                            @AuthenticationPrincipal UserDetails userDetails) {

        commentService.likeComment(commentId, userDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}/like")
    public ResponseEntity<Void> unlikeComment(@PathVariable String commentId,
                                              @AuthenticationPrincipal UserDetails userDetails) {

        commentService.unlikeComment(commentId, userDetails);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comments/{commentId}/pin")
    public ResponseEntity<Void> pinComment(@PathVariable String commentId,
                                           @AuthenticationPrincipal UserDetails userDetails) {

        commentService.pinComment(commentId, userDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}/pin")
    public ResponseEntity<Void> unpinComment(@PathVariable String commentId,
                                             @AuthenticationPrincipal UserDetails userDetails) {


        commentService.unpinComment(commentId, userDetails);
        return ResponseEntity.ok().build();
    }

}
