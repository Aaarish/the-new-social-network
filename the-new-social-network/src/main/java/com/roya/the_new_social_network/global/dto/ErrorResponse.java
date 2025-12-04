package com.roya.the_new_social_network.global.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;

}
