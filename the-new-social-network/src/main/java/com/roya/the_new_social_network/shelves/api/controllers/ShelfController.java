package com.roya.the_new_social_network.shelves.api.controllers;

import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import com.roya.the_new_social_network.shelves.Shelf;
import com.roya.the_new_social_network.shelves.api.dto.request.CreateShelfRequest;
import com.roya.the_new_social_network.shelves.api.dto.response.ShelfResponse;
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
public class ShelfController {
    private final ShelfService shelfService;

    /**
     * Create a new shelf
     * POST /api/v1/shelves
     */
    @PostMapping
    public ResponseEntity<ShelfResponse> createShelf(@RequestBody @Valid CreateShelfRequest request) {
        Shelf shelf = shelfService.createShelf(
                request.getProfileId(),
                request.getProjectId(),
                request.getParentShelfId(),
                request.getCategory(),
                request.getBanner(),
                request.getVisibility()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(shelf.toShelfResponse());
    }

    /**
     * Get a shelf by ID
     * GET /api/v1/shelves/{shelfId}
     */
    @GetMapping("/{shelfId}")
    public ResponseEntity<ShelfResponse> getShelf(@PathVariable @NotBlank String shelfId) {
        Shelf shelf = shelfService.getShelf(shelfId);
        return ResponseEntity.ok(shelf.toShelfResponse());
    }

    /**
     * Get all shelves of a profile
     * GET /api/v1/shelves/profile/{profileId}
     */
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ShelfResponse>> getShelvesOfProfile(@PathVariable @NotBlank String profileId) {
        List<Shelf> shelves = shelfService.getShelvesOfProfile(profileId);
        List<ShelfResponse> responses = shelves.stream()
                .map(Shelf::toShelfResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Get all shelves of a project
     * GET /api/v1/shelves/project/{projectId}
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ShelfResponse>> getShelvesOfProject(@PathVariable @NotBlank String projectId) {
        List<Shelf> shelves = shelfService.getShelvesOfProject(projectId);
        List<ShelfResponse> responses = shelves.stream()
                .map(Shelf::toShelfResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Get all shelves of a project member
     * GET /api/v1/shelves/profile/{profileId}/project/{projectId}
     */
    @GetMapping("/profile/{profileId}/project/{projectId}")
    public ResponseEntity<List<ShelfResponse>> getShelvesOfProjectMember(@PathVariable @NotBlank String profileId,
                                                                         @PathVariable @NotBlank String projectId) {
        List<Shelf> shelves = shelfService.getShelvesOfProjectMember(profileId, projectId);
        List<ShelfResponse> responses = shelves.stream()
                .map(Shelf::toShelfResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Delete a shelf
     * DELETE /api/v1/shelves/{shelfId}
     */
    @DeleteMapping("/{shelfId}")
    public ResponseEntity<GlobalDeleteResponse> deleteShelf(@PathVariable @NotBlank String shelfId) {
        shelfService.deleteShelf(shelfId);

        GlobalDeleteResponse response = GlobalDeleteResponse.builder()
                .success(true)
                .message("Shelf deleted successfully")
                .resourceId(shelfId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shelfId}/sub-shelves")
    public ResponseEntity<List<ShelfResponse>> getSubShelves(@PathVariable String shelfId) {
        // Get all direct sub-shelves
        List<ShelfResponse> shelfResponses = shelfService.getSubShelves(shelfId).stream()
                .map(Shelf::toShelfResponse)
                .toList();

        return ResponseEntity.ok(shelfResponses);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<ShelfResponse>> searchShelves(
//            @RequestParam String query,
//            @RequestParam(required = false) String profileId,
//            @RequestParam(required = false) String projectId,
//            @RequestParam(required = false) ComponentVisibility visibility) {
//        // Search shelves by various criteria
//    }

}
