package com.roya.the_new_social_network.apprenticeship.api.dto.response;


import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorInfo {
    private String mentorId;
    private String profileId;
    private String name;
    private String username;
    private int apprenticeCount;
    private LocalDateTime since;

}
