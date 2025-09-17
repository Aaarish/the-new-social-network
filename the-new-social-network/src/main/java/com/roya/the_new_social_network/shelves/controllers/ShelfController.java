package com.roya.the_new_social_network.shelves.controllers;

import com.roya.the_new_social_network.shelves.sections.SectionRequestDto;
import com.roya.the_new_social_network.shelves.services.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShelfController {
    private final ShelfService shelfService;

    @PostMapping("/projects/{projectId}")
    public ResponseEntity<String> createShelfForProject(@PathVariable String projectId, @RequestBody ShelfRequestDto shelfRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shelfService.createShelfForProject(projectId, shelfRequest));
    }

    @PostMapping("/profiles/{profileId}")
    public ResponseEntity<String> createShelfForProfile(@PathVariable String profileId, @RequestBody ShelfRequestDto shelfRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shelfService.createShelfForProfile(profileId, shelfRequest));
    }

    @PostMapping("/{shelfId}")
    public ResponseEntity<String> addSectionToShelf(@PathVariable String shelfId, @RequestBody SectionRequestDto sectionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shelfService.addSectionToShelf(shelfId, sectionRequest));
    }

    @DeleteMapping("/{shelfId}/sections/{sectionId}")
    public ResponseEntity<String> updateSectionInShelf(@PathVariable String shelfId, @PathVariable String sectionId, SectionRequestDto sectionRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(shelfService.updateSectionInShelf(shelfId, sectionId, sectionRequest));
    }

    @DeleteMapping("/{shelfId}/sections/{sectionId}")
    public ResponseEntity<String> removeSectionFromShelf(@PathVariable String shelfId, @PathVariable String sectionId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(shelfService.removeSectionFromShelf(shelfId, sectionId));
    }

}
