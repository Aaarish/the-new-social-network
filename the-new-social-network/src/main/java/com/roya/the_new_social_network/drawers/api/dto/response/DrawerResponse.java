package com.roya.the_new_social_network.drawers.api.dto.response;

import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrawerResponse {
    private String id;
    private String projectId;
    private String profileId;
    private String shelfId;
    private ComponentVisibility visibility;
    private int noteCount;
    private boolean isPersonal;

}
