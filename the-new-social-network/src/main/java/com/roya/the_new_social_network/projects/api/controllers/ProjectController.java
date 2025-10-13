package com.roya.the_new_social_network.projects.api.controllers;

import com.roya.the_new_social_network.global.utils.CommonUtils;
import com.roya.the_new_social_network.projects.ProjectEntity;
import com.roya.the_new_social_network.projects.api.dto.response.ProjectDeleteResponse;
import com.roya.the_new_social_network.projects.api.dto.request.ProjectRequest;
import com.roya.the_new_social_network.projects.api.dto.response.ProjectMemberResponse;
import com.roya.the_new_social_network.projects.api.dto.response.ProjectResponse;
import com.roya.the_new_social_network.projects.members.ProjectMember;
import com.roya.the_new_social_network.projects.services.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final CommonUtils utils;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest requestDto) {
        ProjectEntity project = projectService.createProject(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(project.toResponse());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable @NotBlank String projectId) {
        ProjectEntity project = projectService.getProject(projectId);
        return ResponseEntity.ok(project.toResponse());
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable @NotBlank String projectId,
                                                         @RequestParam @NotBlank String profileId,
                                                         @RequestBody @Valid ProjectRequest requestDto) {
        ProjectEntity project = projectService.updateProject(profileId, projectId, requestDto);
        return ResponseEntity.ok(project.toResponse());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ProjectDeleteResponse> deleteProject(@PathVariable @NotBlank String projectId,
                                                               @RequestParam @NotBlank String profileId) {

        projectService.deleteProject(profileId, projectId);

        ProjectDeleteResponse response = ProjectDeleteResponse.builder()
                .isSuccess(true)
                .message("Project deleted successfully")
                .projectId(projectId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

//    @GetMapping
//    public ResponseEntity<Page<ProjectResponse>> getAllProjects(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "20") int size,
//            @RequestParam(required = false) PreferenceCategory category,
//            @RequestParam(required = false) ProjectJoiningStrategy strategy) {
//        // Get paginated list of projects with optional filters
//    }


//    @GetMapping("/{projectId}/statistics")
//    public ResponseEntity<ProjectStatistics> getProjectStatistics(@PathVariable String projectId,
//                                                                  @RequestParam String totalPosts,
//                                                                  @RequestParam String totalMembers,
//                                                                  @RequestParam String postsLastNDays,
//                                                                  @RequestParam String membersLastNDays) {
//
//    }


//    @GetMapping("/search")
//    public ResponseEntity<List<ProjectResponse>> searchProjects(
//            @RequestParam String query,
//            @RequestParam(defaultValue = "10") int limit) {
//        // Search projects by title or description
//    }

    @GetMapping("/user/{profileId}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsForProfile(@PathVariable String profileId) {
        return ResponseEntity.ok(projectService.getAllProjectsForProfile(profileId));
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<ProjectMemberResponse>> getProjectMembers(@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.getProjectMembers(projectId).stream()
                .map(ProjectMember::toProjectMemberResponse)
                .toList());
    }

}
