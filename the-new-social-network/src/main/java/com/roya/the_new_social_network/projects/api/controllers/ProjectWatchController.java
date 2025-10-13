package com.roya.the_new_social_network.projects.api.controllers;

import com.roya.the_new_social_network.profiles.ProfileEntity;
import com.roya.the_new_social_network.projects.api.dto.response.WatchResponse;
import com.roya.the_new_social_network.projects.api.dto.response.WatchStatusResponse;
import com.roya.the_new_social_network.projects.api.dto.response.WatchersResponse;
import com.roya.the_new_social_network.projects.services.ProjectWatchService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects/watch")
@RequiredArgsConstructor
public class ProjectWatchController {
    private final ProjectWatchService projectWatchService;

    /**
     * Watch a project (follow/subscribe to project updates)
     * POST /api/v1/projects/{projectId}/watch
     */
    @PostMapping("/{projectId}/watch/{profileId}")
    public ResponseEntity<WatchResponse> watchProject(
            @PathVariable @NotBlank String projectId,
            @PathVariable @NotBlank String profileId) {

        projectWatchService.watch(profileId, projectId);

        WatchResponse response = WatchResponse.builder()
                .isSuccess(true)
                .message("Successfully started watching project")
                .projectId(projectId)
                .profileId(profileId)
                .watching(true)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Unwatch a project (unfollow/unsubscribe from project updates)
     * DELETE /api/v1/projects/{projectId}/watch
     */
    @DeleteMapping("/{projectId}/watch")
    public ResponseEntity<WatchResponse> unwatchProject(
            @PathVariable @NotBlank String projectId,
            @PathVariable @NotBlank String profileId) {

        projectWatchService.unWatch(profileId, projectId);

        WatchResponse response = WatchResponse.builder()
                .isSuccess(true)
                .message("Successfully stopped watching project")
                .projectId(projectId)
                .profileId(profileId)
                .watching(false)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{projectId}/watchers")
    public ResponseEntity<WatchersResponse> getWatchers(
            @PathVariable String projectId) {

        List<ProfileEntity> watchers = projectWatchService.getWatchers(projectId);
        int count = projectWatchService.getWatcherCount(projectId);

        return ResponseEntity.ok(WatchersResponse.builder()
                .watchers(watchers)
                .count(count)
                .build());
    }

    @GetMapping("/{projectId}/watch/status")
    public ResponseEntity<WatchStatusResponse> getWatchStatus(
            @PathVariable String projectId,
            @RequestParam String profileId) {

        boolean watching = projectWatchService.isWatching(profileId, projectId);

        return ResponseEntity.ok(WatchStatusResponse.builder()
                .isWatching(watching)
                .projectId(projectId)
                .profileId(profileId)
                .build());
    }

}
