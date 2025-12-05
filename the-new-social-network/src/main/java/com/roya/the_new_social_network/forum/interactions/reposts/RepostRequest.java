package com.roya.the_new_social_network.forum.interactions.reposts;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RepostRequest {
    @NotBlank
    private String quote;

}
