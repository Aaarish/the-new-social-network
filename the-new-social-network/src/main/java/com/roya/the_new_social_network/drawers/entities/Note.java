package com.roya.the_new_social_network.drawers.entities;

import com.roya.the_new_social_network.drawers.api.dto.response.NoteResponse;
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

    public NoteResponse toNoteResponse() {
        return NoteResponse.builder()
                .id(this.getId())
                .title(this.getTitle())
                .content(this.getContent())
                .drawerId(this.getDrawer() != null ? this.getDrawer().getId() : null)
                .build();
    }

}
