package com.roya.the_new_social_network.shelves.services;

import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import com.roya.the_new_social_network.global.utils.CommonUtils;
import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.shelves.*;
import com.roya.the_new_social_network.shelves.sections.Section;
import com.roya.the_new_social_network.shelves.sections.SectionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelfServiceImpl implements ShelfService {
    private final ShelfDao shelfDao;
    private final SectionDao sectionDao;

    private final CommonUtils utils;

    @Override
    public Shelf createShelf(String profileId, String projectId, String parentShelfId,
                              String category, String banner, ComponentVisibility visibility) {

        Shelf parentShelf = returnIfShelfExists(parentShelfId);

        ProfileEntity profile = utils.returnProfileFromId(profileId);
        ProjectEntity project = utils.returnProjectFromId(projectId);

        Shelf shelf = Shelf.builder()
                .manager(profile)
                .project(project)
                .parentShelf(parentShelf)
                .banner(banner)
                .category(category)
                .visibility(visibility)
                .build();

        return shelfDao.save(shelf);
    }

    @Override
    public Shelf getShelf(String shelfId) {
        return returnIfShelfExists(shelfId);
    }

    @Override
    public List<Shelf> getShelvesOfProfile(String profileId) {
        ProfileEntity manager = utils.returnProfileFromId(profileId);

        return shelfDao.findByManager(manager);
    }

    @Override
    public List<Shelf> getShelvesOfProject(String projectId) {
        ProjectEntity project = utils.returnProjectFromId(projectId);

        return shelfDao.findByProject(project);
    }

    @Override
    public List<Shelf> getShelvesOfProjectMember(String profileId, String projectId) {
        ProjectEntity project = utils.returnProjectFromId(projectId);
        ProfileEntity profile = utils.returnProfileFromId(profileId);

        return shelfDao.findByProjectAndManager(project, profile);
    }

    @Override
    @Transactional
    public Section addSectionToShelf(String shelfId, String heading, String content, String image, String url) {
        Shelf shelf = returnIfShelfExists(shelfId);

        Section section = Section.builder()
                .shelf(shelf)
                .heading(heading)
                .content(content)
                .media(List.of(image))
                .urls(List.of(url))
                .build();

        Section savedSection = sectionDao.save(section);
        shelf.getSections().add(savedSection);
        return savedSection;
    }

    @Override
    public Section getSection(String sectionId) {
        return returnIfSectionExists(sectionId);
    }

    @Override
    public List<Section> getSectionsOfShelf(String shelfId) {
        Shelf shelf = returnIfShelfExists(shelfId);

        return shelf.getSections();
    }

    @Override
    @Transactional
    public Section updateSectionInShelf(String shelfId, String sectionId,
                                        String heading, String content, String image, String url) {
        return null;
    }

    @Override
    @Transactional
    public void removeSectionFromShelf(String shelfId, String sectionId) {
        Shelf shelf = returnIfShelfExists(shelfId);
        Section section = returnIfNoteExistsInDrawer(sectionId, shelf);

        shelf.getSections().remove(section);
        sectionDao.delete(section);
    }

    @Override
    @Transactional
    public void deleteShelf(String shelfId) {
        Shelf shelf = returnIfShelfExists(shelfId);
        shelfDao.delete(shelf);
    }

    @Override
    public List<Shelf> getSubShelves(String shelfId) {
        Shelf shelf = returnIfShelfExists(shelfId);
        return shelf.getSubShelves();
    }

    private Shelf returnIfShelfExists(String shelfId) {
        return shelfDao.findById(shelfId)
                .orElseThrow(() -> new IllegalArgumentException("Shelf not found"));
    }

    private Section returnIfSectionExists(String sectionId) {
        return sectionDao.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
    }

    private Section returnIfNoteExistsInDrawer(String sectionId, Shelf shelf) {
        Section section = returnIfSectionExists(sectionId);

        return shelf.getSections().stream()
                .filter(s -> s == section)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note does not exist in this drawer"));
    }
}
