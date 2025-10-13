package com.roya.the_new_social_network.projects.api.controllers;

import com.roya.the_new_social_network.projects.api.dto.response.ApplicationActionResponse;
import com.roya.the_new_social_network.projects.api.dto.request.JoinProjectRequest;
import com.roya.the_new_social_network.projects.api.dto.response.JoinProjectResponse;
import com.roya.the_new_social_network.projects.services.ProjectJoiningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/projects/join")
@RequiredArgsConstructor
public class ProjectJoiningController {
    private final ProjectJoiningService projectJoiningService;

    /**
     * Join a project (for open projects) or submit application (for application-based projects)
     * POST /api/v1/projects/{projectId}/join
     */
    @PostMapping("/{projectId}/join")
    public ResponseEntity<JoinProjectResponse> joinProject(
            @PathVariable @NotBlank String projectId,
            @RequestBody @Valid JoinProjectRequest request) {

        boolean joined = projectJoiningService.joinProject(projectId, request.getProfileId());

        JoinProjectResponse response = JoinProjectResponse.builder()
                .success(joined)
                .message(joined ? "Successfully joined project" : "Application submitted for review")
                .projectId(projectId)
                .profileId(request.getProfileId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Approve a project application
     * PUT /api/v1/projects/applications/{applicationId}/approve
     */
    @PutMapping("/applications/{applicationId}/approve")
    public ResponseEntity<ApplicationActionResponse> approveApplication(
            @PathVariable @NotBlank String applicationId) {

        projectJoiningService.approveApplication(applicationId);

        ApplicationActionResponse response = ApplicationActionResponse.builder()
                .success(true)
                .message("Application approved successfully")
                .applicationId(applicationId)
                .action("APPROVED")
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Reject a project application
     * PUT /api/v1/projects/applications/{applicationId}/reject
     */
    @PutMapping("/applications/{applicationId}/reject")
    public ResponseEntity<ApplicationActionResponse> rejectApplication(
            @PathVariable @NotBlank String applicationId) {

        projectJoiningService.rejectApplication(applicationId);

        ApplicationActionResponse response = ApplicationActionResponse.builder()
                .success(true)
                .message("Application rejected successfully")
                .applicationId(applicationId)
                .action("REJECTED")
                .build();

        return ResponseEntity.ok(response);
    }

}
