package com.roya.the_new_social_network.shelves;

import com.roya.the_new_social_network.global.ComponentVisibility;
import com.roya.the_new_social_network.shelves.sections.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "shelves")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String shelfId;

    @Column(name = "resource", nullable = false)
    private Integer resourceTypeId;

    @Column(name = "resource_id", nullable = false)
    private String resourceId;

    private String category;
    private String banner;

    @Enumerated(EnumType.STRING)
    private ComponentVisibility visibility;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shelf", orphanRemoval = true)
    @Builder.Default
    private List<Section> sections = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shelf parentShelf;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parentShelf", orphanRemoval = true)
    @Builder.Default
    private List<Shelf> subShelves = new ArrayList<>();

}
