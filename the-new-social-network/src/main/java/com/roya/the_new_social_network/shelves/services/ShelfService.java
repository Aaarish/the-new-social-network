package com.roya.the_new_social_network.shelves.services;

import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import com.roya.the_new_social_network.shelves.Shelf;
import com.roya.the_new_social_network.shelves.sections.Section;

import java.util.List;

public interface ShelfService {

    Shelf createShelf(String profileId, String projectId, String parentShelfId, String category, String banner, ComponentVisibility visibility);

    Shelf getShelf(String shelfId);

    List<Shelf> getShelvesOfProfile(String profileId);

    List<Shelf> getShelvesOfProject(String projectId);

    List<Shelf> getShelvesOfProjectMember(String profileId, String projectId);

    Section addSectionToShelf(String shelfId, String heading, String content, String image, String url);

    Section getSection(String sectionId);

    List<Section> getSectionsOfShelf(String shelfId);

    Section updateSectionInShelf(String shelfId, String sectionId, String heading, String content, String image, String url);

    void removeSectionFromShelf(String shelfId, String sectionId);

    void deleteShelf(String shelfId);

    List<Shelf> getSubShelves(String shelfId);
}
