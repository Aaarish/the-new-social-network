package com.roya.the_new_social_network.profiles.api.dto.response;

import com.roya.the_new_social_network.apprenticeship.api.dto.response.ApprenticeInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileApprenticesResponse {
    private String profileId;
    private String username;
    private List<ApprenticeInfo> apprentices;
    private int totalApprentices;

}
