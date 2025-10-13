package com.roya.the_new_social_network.profiles.api.dto.response;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedProjectInfo {
    private String projectId;
    private String projectTitle;
    private String projectDescription;
    private int memberCount;
    private LocalDateTime createdAt;

}
