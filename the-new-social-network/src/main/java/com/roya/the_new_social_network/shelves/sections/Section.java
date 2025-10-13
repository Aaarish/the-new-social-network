package com.roya.the_new_social_network.shelves.sections;

import com.roya.the_new_social_network.forum.media.Media;
import com.roya.the_new_social_network.shelves.Shelf;
import com.roya.the_new_social_network.shelves.api.dto.response.SectionResponse;
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

    public SectionResponse toSectionResponse() {
        return SectionResponse.builder()
                .sectionId(this.getSectionId())
                .shelfId(this.getShelf() != null ? this.getShelf().getShelfId() : null)
                .heading(this.getHeading())
                .content(this.getContent())
                .media(this.getMedia())
                .urls(this.getUrls())
                .mediaCount(this.getMedia() != null ? this.getMedia().size() : 0)
                .urlCount(this.getUrls() != null ? this.getUrls().size() : 0)
                .build();
    }

}
