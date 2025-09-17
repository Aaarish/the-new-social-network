package com.roya.the_new_social_network.shelves.services;

import com.roya.the_new_social_network.global.AppConstants;
import com.roya.the_new_social_network.shelves.*;
import com.roya.the_new_social_network.shelves.controllers.ShelfRequestDto;
import com.roya.the_new_social_network.shelves.sections.Section;
import com.roya.the_new_social_network.shelves.sections.SectionDao;
import com.roya.the_new_social_network.shelves.sections.SectionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShelfServiceImpl implements ShelfService {
    private final ShelfDao shelfDao;
    private final SectionDao sectionDao;

    @Override
    public String createShelfForProject(String projectId, ShelfRequestDto shelfRequest) {
        Shelf parentShelf = null;
        if (shelfRequest.getParentShelfId() != null) {
            Optional<Shelf> foundParent = shelfDao.findById(shelfRequest.getParentShelfId());
            if (foundParent.isPresent()) parentShelf = foundParent.get();
        }

        Shelf shelf = Shelf.builder()
                .resourceTypeId(AppConstants.PROJECT_TYPE_ID)
                .resourceId(projectId)
                .parentShelf(parentShelf)
                .visibility(shelfRequest.getVisibility())
                .category(shelfRequest.getCategory())
                .banner(shelfRequest.getBanner())
                .build();

        return shelf.getShelfId();
    }

    @Override
    public String createShelfForProfile(String profileId, ShelfRequestDto shelfRequest) {
        Shelf parentShelf = null;
        if (shelfRequest.getParentShelfId() != null) {
            Optional<Shelf> foundParent = shelfDao.findById(shelfRequest.getParentShelfId());
            if (foundParent.isPresent()) parentShelf = foundParent.get();
        }

        Shelf shelf = Shelf.builder()
                .resourceTypeId(AppConstants.PROFILE_TYPE_ID)
                .resourceId(profileId)
                .parentShelf(parentShelf)
                .visibility(shelfRequest.getVisibility())
                .category(shelfRequest.getCategory())
                .banner(shelfRequest.getBanner())
                .build();

        return shelf.getShelfId();
    }

    @Override
    public String addSectionToShelf(String shelfId, SectionRequestDto sectionRequest) {
        Shelf shelf = returnIfShelfExists(shelfId);

        Section.builder()
                .shelf(shelf)
                .heading(sectionRequest.getHeading())
                .content(sectionRequest.getContent())
                .build();

        return null;
    }

    @Override
    @Transactional
    public String updateSectionInShelf(String shelfId, String sectionId, SectionRequestDto sectionRequest) {
        Section section = returnIfSectionExists(sectionId);

        if (!section.getShelf().getShelfId().equals(shelfId)) return "Invalid Request: section does not belong to the specified shelf";

        if (sectionRequest.getHeading() != null && !sectionRequest.getHeading().trim().isEmpty()) section.setHeading(sectionRequest.getHeading());
        if (sectionRequest.getContent() != null && !sectionRequest.getHeading().trim().isEmpty()) section.setContent(sectionRequest.getContent());
        if (sectionRequest.getImage() != null) section.getImages().add(sectionRequest.getImage());
        if (sectionRequest.getUrl() != null) section.getUrls().add(sectionRequest.getUrl());

        return sectionId;
    }

    @Override
    @Transactional
    public String removeSectionFromShelf(String shelfId, String sectionId) {
        Section section = returnIfSectionExists(sectionId);

        Shelf shelf = section.getShelf();

        if (!shelf.getShelfId().equals(shelfId)) return "Invalid Request: section does not belong to the specified shelf";

        shelf.getSections().remove(section);
        sectionDao.delete(section);

        return sectionId;
    }

    private Shelf returnIfShelfExists(String shelfId) {
        return shelfDao.findById(shelfId)
                .orElseThrow(() -> new IllegalArgumentException("Shelf not found"));
    }

    private Section returnIfSectionExists(String sectionId) {
        return sectionDao.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
    }
}
