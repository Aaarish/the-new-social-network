package com.roya.the_new_social_network.profiles.api.controllers;

import com.roya.the_new_social_network.profiles.*;
import com.roya.the_new_social_network.profiles.api.dto.request.PreferenceRequest;
import com.roya.the_new_social_network.profiles.api.dto.request.UpdateProfileRequest;
import com.roya.the_new_social_network.profiles.api.dto.response.*;
import com.roya.the_new_social_network.profiles.preferences.Preference;
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
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    /**
     * Get a profile by ID
     * GET /api/v1/profiles/{profileId}
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable @NotBlank String profileId) {
        ProfileEntity profile = profileService.getProfile(profileId);
        return ResponseEntity.ok(profile.toProfileResponse());
    }

    /**
     * Get all projects for a profile (as member)
     * GET /api/v1/profiles/{profileId}/projects
     */
    @GetMapping("/{profileId}/projects")
    public ResponseEntity<ProfileProjectsResponse> getProjectsForProfile(@PathVariable @NotBlank String profileId) {
        return ResponseEntity.ok(profileService.getProjectsForProfile(profileId));
    }

    /**
     * Get created projects for a profile (as owner/creator)
     * GET /api/v1/profiles/{profileId}/projects/created
     */
    @GetMapping("/{profileId}/projects/created")
    public ResponseEntity<ProfileCreatedProjectsResponse> getCreatedProjectsForProfile(@PathVariable @NotBlank String profileId) {
        return ResponseEntity.ok(profileService.getCreatedProjectsForProfile(profileId));
    }

    /**
     * Get apprentices of a profile (people learning from this profile)
     * GET /api/v1/profiles/{profileId}/apprentices
     */
    @GetMapping("/{profileId}/apprentices")
    public ResponseEntity<ProfileApprenticesResponse> getApprenticesOfProfile(@PathVariable @NotBlank String profileId) {
        return ResponseEntity.ok(profileService.getApprenticesOfProfile(profileId));
    }

    /**
     * Get mentors of a profile (people teaching this profile)
     * GET /api/v1/profiles/{profileId}/mentors
     */
    @GetMapping("/{profileId}/mentors")
    public ResponseEntity<ProfileMentorsResponse> getMentorsOfProfile(@PathVariable @NotBlank String profileId) {
        return ResponseEntity.ok(profileService.getMentorsOfProfile(profileId));
    }

    /**
     * Delete a profile
     * DELETE /api/v1/profiles/{profileId}
     */
    @DeleteMapping("/{profileId}")
    public ResponseEntity<ProfileDeleteResponse> deleteProfile(@PathVariable @NotBlank String profileId) {
        profileService.deleteProfile(profileId);

        ProfileDeleteResponse response = ProfileDeleteResponse.builder()
                .success(true)
                .message("Profile deleted successfully")
                .profileId(profileId)
                .deletedAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable String profileId,
                                                         @RequestBody @Valid UpdateProfileRequest request) {
        ProfileEntity profile = profileService.updateProfile(profileId, request);
        return ResponseEntity.ok(profile.toProfileResponse());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ProfileResponse> getProfileByUsername(@PathVariable String username) {
        ProfileEntity profile = profileService.getProfileByUsername(username);
        return ResponseEntity.ok(profile.toProfileResponse());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProfileResponse>> searchProfiles(@RequestParam String query,
                                                                @RequestParam(defaultValue = "10") int limit) {
        List<ProfileEntity> profiles = profileService.searchProfiles(query);
        List<ProfileResponse> responses = profiles.stream()
                .limit(limit)
                .map(ProfileEntity::toProfileResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{profileId}/preferences")
    public ResponseEntity<List<PreferenceResponse>> getProfilePreferences(
            @PathVariable String profileId) {

        List<Preference> preferences = profileService.getProfilePreferences(profileId);
        List<PreferenceResponse> responses = preferences.stream()
                .map(p -> PreferenceResponse.builder()
                        .preferenceId(p.getPrefId())
                        .interestLevel(p.getInterestLevel())
                        .description(p.getDescription())
                        .category(p.getCategory())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{profileId}/preferences")
    public ResponseEntity<Void> addPreference(@PathVariable String profileId, @RequestBody PreferenceRequest request) {
        profileService.addPreference(profileId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{profileId}/preferences/{preferenceId}")
    public ResponseEntity<Void> removePreference(@PathVariable String profileId, @PathVariable String preferenceId) {
        profileService.removePreference(profileId, preferenceId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{profileId}/posts")
//    public ResponseEntity<Page<PostResponse>> getProfilePosts(@PathVariable String profileId,
//                                                              @RequestParam(defaultValue = "0") int page,
//                                                              @RequestParam(defaultValue = "20") int size) {
//
//        List<Post> posts = profileService.getProfilePosts(profileId, page, size);
//    }

//    @GetMapping("/{profileId}/stats")
//    public ResponseEntity<ProfileStatsResponse> getProfileStats(@PathVariable String profileId) {
//        ProfileStatsDto stats = profileService.getProfileStats(profileId);
//
//        ProfileStatsResponse response = ProfileStatsResponse.builder()
//                .profileId(profileId)
//                .totalPosts(stats.getTotalPosts())
//                .totalProjects(stats.getTotalProjects())
//                .totalApprentices(stats.getTotalApprentices())
//                .totalMentors(stats.getTotalMentors())
//                .totalApplications(stats.getTotalApplications())
//                .joinedDate(stats.getJoinedDate())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }

}
