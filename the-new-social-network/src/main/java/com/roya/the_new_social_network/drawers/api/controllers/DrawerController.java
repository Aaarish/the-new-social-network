package com.roya.the_new_social_network.drawers.api.controllers;

import com.roya.the_new_social_network.drawers.entities.Drawer;
import com.roya.the_new_social_network.drawers.api.dto.response.DrawerResponse;
import com.roya.the_new_social_network.drawers.api.dto.request.CreateDrawerRequest;
import com.roya.the_new_social_network.drawers.services.DrawerService;
import com.roya.the_new_social_network.global.dto.GlobalDeleteResponse;
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
@RequestMapping("/api/v1/drawers")
@RequiredArgsConstructor
public class DrawerController {
    private final DrawerService drawerService;

    /**
     * Create a new drawer
     * POST /api/v1/drawers
     */
    @PostMapping
    public ResponseEntity<DrawerResponse> createDrawer(@RequestBody @Valid CreateDrawerRequest request) {

        Drawer drawer = drawerService.createDrawer(
                request.getProfileId(),
                request.getProjectId(),
                request.getShelfId(),
                request.getVisibility()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(drawer.toDrawerResponse());
    }

    /**
     * Get a drawer by ID
     * GET /api/v1/drawers/{drawerId}
     */
    @GetMapping("/{drawerId}")
    public ResponseEntity<DrawerResponse> getDrawer(@PathVariable @NotBlank String drawerId) {
        Drawer drawer = drawerService.getDrawer(drawerId);
        return ResponseEntity.ok(drawer.toDrawerResponse());
    }

    /**
     * Get all drawers of a project
     * GET /api/v1/drawers/project/{projectId}
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<DrawerResponse>> getDrawersOfProject(@PathVariable @NotBlank String projectId) {
        List<Drawer> drawers = drawerService.getDrawersOfProject(projectId);
        List<DrawerResponse> responses = drawers.stream()
                .map(Drawer::toDrawerResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Get all drawers of a profile
     * GET /api/v1/drawers/profile/{profileId}
     */
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<DrawerResponse>> getDrawersOfProfile(@PathVariable @NotBlank String profileId) {
        List<Drawer> drawers = drawerService.getDrawersOfProfile(profileId);
        List<DrawerResponse> responses = drawers.stream()
                .map(Drawer::toDrawerResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Get all drawers of a project member
     * GET /api/v1/drawers/profile/{profileId}/project/{projectId}
     */
    @GetMapping("/profile/{profileId}/project/{projectId}")
    public ResponseEntity<List<DrawerResponse>> getDrawersOfProjectMember(
            @PathVariable @NotBlank String profileId,
            @PathVariable @NotBlank String projectId) {

        List<Drawer> drawers = drawerService.getDrawersOfProjectMember(profileId, projectId);
        List<DrawerResponse> responses = drawers.stream()
                .map(Drawer::toDrawerResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    /**
     * Link a shelf to a drawer
     * PUT /api/v1/drawers/{drawerId}/shelf/{shelfId}
     */
    @PutMapping("/{drawerId}/shelf/{shelfId}")
    public ResponseEntity<DrawerResponse> linkShelfToDrawer(
            @PathVariable @NotBlank String drawerId,
            @PathVariable @NotBlank String shelfId) {

        Drawer drawer = drawerService.linkShelfToDrawer(drawerId, shelfId);
        return ResponseEntity.ok(drawer.toDrawerResponse());
    }

    /**
     * Delete a drawer
     * DELETE /api/v1/drawers/{drawerId}
     */
    @DeleteMapping("/{drawerId}")
    public ResponseEntity<GlobalDeleteResponse> removeDrawer(@PathVariable @NotBlank String drawerId) {
        drawerService.removeDrawer(drawerId);

        GlobalDeleteResponse response = GlobalDeleteResponse.builder()
                .success(true)
                .message("Drawer deleted successfully")
                .resourceId(drawerId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

//    @PatchMapping("/{drawerId}/visibility")
//    public ResponseEntity<DrawerResponse> updateVisibility(@PathVariable String drawerId,
//                                                           @RequestBody UpdateVisibilityRequest request) {
//        // Update drawer visibility
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<DrawerResponse>> searchDrawers(@RequestParam String query,
//                                                              @RequestParam(required = false) String profileId,
//                                                              @RequestParam(required = false) String projectId) {
//        // Search drawers by various criteria
//    }

}
