package com.roya.the_new_social_network.drawers;

import com.roya.the_new_social_network.global.ComponentVisibility;
import jakarta.persistence.Column;
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

    @Column(name = "resource", nullable = false)
    private Integer resourceTypeId;

    @Column(name = "resource_id", nullable = false)
    private String resourceId;

    @Setter private String shelfId;

    @Enumerated(EnumType.STRING)
    private ComponentVisibility visibility;

    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Note> notes = new ArrayList<>();

}
