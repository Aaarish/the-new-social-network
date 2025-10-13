package com.roya.the_new_social_network.drawers.api.dto.request;

import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDrawerRequest {
    private String profileId;

    private String projectId; // null for personal drawers

    private String shelfId; // optional, can be linked later

    @Builder.Default
    private ComponentVisibility visibility = ComponentVisibility.PUBLIC;

}
