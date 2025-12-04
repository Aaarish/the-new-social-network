package com.roya.the_new_social_network.drawers.entities;

import com.roya.the_new_social_network.drawers.api.dto.response.DrawerResponse;
import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "drawers")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Drawer {
    @Id
    private String id;

    private String projectId; // projectId = null means it's a personal drawer, else it belongs to a project

    private String profileId;

    @Setter private String shelfId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ComponentVisibility visibility = ComponentVisibility.PUBLIC;

    // needs to add mapping to notes
    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Note> notes = new ArrayList<>();

    public DrawerResponse toDrawerResponse() {
        return DrawerResponse.builder()
                .id(this.getId())
                .projectId(this.getProjectId())
                .profileId(this.getProfileId())
                .shelfId(this.getShelfId())
                .visibility(this.getVisibility())
                .noteCount(this.getNotes() != null ? this.getNotes().size() : 0)
                .isPersonal(this.getProjectId() == null)
                .build();
    }

}
