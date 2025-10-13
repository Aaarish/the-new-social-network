package com.roya.the_new_social_network.shelves;

import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.shelves.api.dto.response.ShelfResponse;
import com.roya.the_new_social_network.shelves.sections.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "shelves")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Shelf {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String shelfId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false, updatable = false)
    private ProjectEntity project; // project = null means it's a personal shelf, else it belongs to a project

    @OneToOne
    @Column(name = "manager_id", nullable = false)
    private ProfileEntity manager; // project member if it belongs to a project, else profile

    private String category;

    private String banner;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ComponentVisibility visibility = ComponentVisibility.PUBLIC;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shelf", orphanRemoval = true)
    @Builder.Default
    private List<Section> sections = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id", updatable = false)
    private Shelf parentShelf;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parentShelf", orphanRemoval = true)
    @Builder.Default
    private List<Shelf> subShelves = new ArrayList<>();

    public ShelfResponse toShelfResponse() {
        return ShelfResponse.builder()
                .shelfId(this.getShelfId())
                .projectId(this.getProject() != null ? this.getProject().getProjectId() : null)
                .managerId(this.getManager() != null ? this.getManager().getProfileId() : null)
                .category(this.getCategory())
                .banner(this.getBanner())
                .visibility(this.getVisibility())
                .sectionCount(this.getSections() != null ? this.getSections().size() : 0)
                .subShelfCount(this.getSubShelves() != null ? this.getSubShelves().size() : 0)
                .parentShelfId(this.getParentShelf() != null ? this.getParentShelf().getShelfId() : null)
                .isPersonal(this.getProject() == null)
                .build();
    }

}
