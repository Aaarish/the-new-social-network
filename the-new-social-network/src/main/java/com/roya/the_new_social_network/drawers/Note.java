package com.roya.the_new_social_network.drawers;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "notes")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Note {
    @Id
    private String id;

    @Setter private String title;
    @Setter private String content;

    @DocumentReference(lazy = true)
    private Drawer drawer;

}
