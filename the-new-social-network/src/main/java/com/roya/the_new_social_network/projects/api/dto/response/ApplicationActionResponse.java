package com.roya.the_new_social_network.projects.api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationActionResponse {
    private boolean success;
    private String message;
    private String applicationId;
    private String action; // "APPROVED" or "REJECTED"

}
