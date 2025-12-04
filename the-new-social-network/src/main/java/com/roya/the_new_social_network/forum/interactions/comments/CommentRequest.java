package com.roya.the_new_social_network.forum.interactions.comments;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentRequest {
    @NotBlank
    String content;

}
