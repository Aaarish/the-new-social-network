package com.roya.the_new_social_network.shelves.services;

import com.roya.the_new_social_network.shelves.sections.SectionRequestDto;
import com.roya.the_new_social_network.shelves.controllers.ShelfRequestDto;

public interface ShelfService {
    String createShelfForProfile(String profileId, ShelfRequestDto shelfRequest);

    String createShelfForProject(String projectId, ShelfRequestDto shelfRequest);

    String addSectionToShelf(String shelfId, SectionRequestDto sectionRequest);

    String updateSectionInShelf(String shelfId, String sectionId, SectionRequestDto sectionRequest);

    String removeSectionFromShelf(String shelfId, String sectionId);

}
