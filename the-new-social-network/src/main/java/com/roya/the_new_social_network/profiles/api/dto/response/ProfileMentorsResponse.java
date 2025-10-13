package com.roya.the_new_social_network.profiles.api.dto.response;

import com.roya.the_new_social_network.apprenticeship.api.dto.response.MentorInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileMentorsResponse {
    private String profileId;
    private String username;
    private List<MentorInfo> mentors;
    private int totalMentors;

}
