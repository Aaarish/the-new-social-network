package com.roya.the_new_social_network.shelves.api.dto.response;


import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShelfResponse {
    private String shelfId;
    private String projectId;
    private String managerId;
    private String category;
    private String banner;
    private ComponentVisibility visibility;
    private int sectionCount;
    private int subShelfCount;
    private String parentShelfId;
    private boolean isPersonal;

}
