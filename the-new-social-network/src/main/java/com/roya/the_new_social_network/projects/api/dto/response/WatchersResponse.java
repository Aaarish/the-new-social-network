package com.roya.the_new_social_network.projects.api.dto.response;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchersResponse {
    private List<ProfileEntity> watchers;
    private int count;

}
