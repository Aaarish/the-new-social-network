package com.roya.the_new_social_network.shelves.api.controllers;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.shelves.api.dto.request.SectionRequest;
import com.roya.the_new_social_network.shelves.api.dto.response.SectionResponse;
import com.roya.the_new_social_network.shelves.sections.Section;
import com.roya.the_new_social_network.shelves.services.ShelfService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SectionController {
    private final ShelfService shelfService;

    /**
     * Add a section to a shelf
     * POST /api/v1/shelves/{shelfId}/sections
     */
    @PostMapping("/{shelfId}/sections")
    public ResponseEntity<SectionResponse> addSectionToShelf(@PathVariable @NotBlank String shelfId,
                                                             @RequestBody @Valid SectionRequest request) {
        Section section = shelfService.addSectionToShelf(
                shelfId,
                request.getHeading(),
                request.getContent(),
                request.getImage(),
                request.getUrl()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(section.toSectionResponse());
    }

    /**
     * Get a section by ID
     * GET /api/v1/shelves/sections/{sectionId}
     */
    @GetMapping("/sections/{sectionId}")
    public ResponseEntity<SectionResponse> getSection(@PathVariable @NotBlank String sectionId) {
        Section section = shelfService.getSection(sectionId);
        return ResponseEntity.ok(section.toSectionResponse());
    }

    /**
     * Get all sections of a shelf
     * GET /api/v1/shelves/{shelfId}/sections
     */
    @GetMapping("/{shelfId}/sections")
    public ResponseEntity<List<SectionResponse>> getSectionsOfShelf(@PathVariable @NotBlank String shelfId) {
        List<Section> sections = shelfService.getSectionsOfShelf(shelfId);
        List<SectionResponse> responses = sections.stream()
                .map(Section::toSectionResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Update a section in a shelf
     * PUT /api/v1/shelves/{shelfId}/sections/{sectionId}
     */
    @PutMapping("/{shelfId}/sections/{sectionId}")
    public ResponseEntity<SectionResponse> updateSectionInShelf(@PathVariable @NotBlank String shelfId,
                                                                @PathVariable @NotBlank String sectionId,
                                                                @RequestBody @Valid SectionRequest request) {
        Section section = shelfService.updateSectionInShelf(
                shelfId,
                sectionId,
                request.getHeading(),
                request.getContent(),
                request.getImage(),
                request.getUrl()
        );

        return ResponseEntity.ok(section.toSectionResponse());
    }

    /**
     * Remove a section from a shelf
     * DELETE /api/v1/shelves/{shelfId}/sections/{sectionId}
     */
    @DeleteMapping("/{shelfId}/sections/{sectionId}")
    public ResponseEntity<GlobalDeleteResponse> removeSectionFromShelf(@PathVariable @NotBlank String shelfId,
                                                                       @PathVariable @NotBlank String sectionId) {

        shelfService.removeSectionFromShelf(shelfId, sectionId);

        GlobalDeleteResponse response = GlobalDeleteResponse.builder()
                .success(true)
                .message("Section deleted successfully")
                .resourceId(sectionId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

}
