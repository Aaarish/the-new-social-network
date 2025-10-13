package com.roya.the_new_social_network.global.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalDeleteResponse {
    private boolean success;
    private String message;
    private String resourceId;
    private LocalDateTime deletedAt;

}
