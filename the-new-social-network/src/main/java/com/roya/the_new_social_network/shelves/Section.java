package com.roya.the_new_social_network.shelves;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Shelf shelf;

    private String heading;
    private String content;
    private List<String> images;
    private List<String> urls;

}
