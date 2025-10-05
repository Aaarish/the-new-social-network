package com.roya.the_new_social_network.drawers;

import com.roya.the_new_social_network.global.ComponentVisibility;
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

    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Note> notes = new ArrayList<>();

}
