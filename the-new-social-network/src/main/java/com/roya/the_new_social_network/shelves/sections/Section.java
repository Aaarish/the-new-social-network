package com.roya.the_new_social_network.shelves.sections;

import com.roya.the_new_social_network.forum.media.Media;
import com.roya.the_new_social_network.shelves.Shelf;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sections")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String sectionId;

    @ManyToOne
    @JoinColumn(name = "shelf_id", referencedColumnName = "id", nullable = false)
    @Setter private Shelf shelf;

    @Setter private String heading;

    @Setter private String content;

    @Builder.Default
    @Setter private List<String> media = new ArrayList<>();

    @Builder.Default
    @Setter private List<String> urls = new ArrayList<>();

}
