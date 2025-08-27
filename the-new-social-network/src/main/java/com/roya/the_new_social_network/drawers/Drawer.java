package com.roya.the_new_social_network.drawers;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter private String shelfId;

    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Note> notes = new ArrayList<>();

}
