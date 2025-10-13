package com.roya.the_new_social_network.apprenticeship.api.controllers;

import com.roya.the_new_social_network.apprenticeship.Apprentice;
import com.roya.the_new_social_network.apprenticeship.Mentor;
import com.roya.the_new_social_network.apprenticeship.api.dto.request.ApprenticeshipRequest;
import com.roya.the_new_social_network.apprenticeship.api.dto.response.ApprenticeInfo;
import com.roya.the_new_social_network.apprenticeship.api.dto.response.ApprenticeshipCheckResponse;
import com.roya.the_new_social_network.apprenticeship.api.dto.response.ApprenticeshipResponse;
import com.roya.the_new_social_network.apprenticeship.api.dto.response.MentorInfo;
import com.roya.the_new_social_network.apprenticeship.services.ApprenticeshipService;
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
@RequestMapping
@RequiredArgsConstructor
public class ApprenticeshipController {
    private final ApprenticeshipService apprenticeshipService;

    @PostMapping("/request")
    public ResponseEntity<ApprenticeshipResponse> requestApprenticeship(@RequestBody @Valid ApprenticeshipRequest request) {
        apprenticeshipService.requestApprenticeship(request.getSourceProfileId(),request.getTargetProfileId());

        ApprenticeshipResponse response = ApprenticeshipResponse.builder()
                .success(true)
                .message("Apprenticeship request successful")
                .apprenticeProfileId(request.getSourceProfileId())
                .mentorProfileId(request.getTargetProfileId())
                .action("CREATED")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Alternative: Remove apprenticeship using path variables
     * DELETE /api/v1/apprenticeships/apprentice/{apprenticeProfileId}/mentor/{mentorProfileId}
     */
    @DeleteMapping("/apprentice/{apprenticeProfileId}/mentor/{mentorProfileId}")
    public ResponseEntity<ApprenticeshipResponse> removeApprenticeship(@PathVariable @NotBlank String apprenticeProfileId,
                                                                       @PathVariable @NotBlank String mentorProfileId) {
        apprenticeshipService.removeApprenticeship(apprenticeProfileId,mentorProfileId);

        ApprenticeshipResponse response = ApprenticeshipResponse.builder()
                .success(true)
                .message("Apprenticeship removed successfully")
                .apprenticeProfileId(apprenticeProfileId)
                .mentorProfileId(mentorProfileId)
                .action("REMOVED")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ResponseEntity<ApprenticeshipCheckResponse> checkApprenticeship(
            @RequestParam String apprenticeProfileId,
            @RequestParam String mentorProfileId) {

        boolean doesExist = apprenticeshipService.hasApprenticeship(apprenticeProfileId, mentorProfileId);

        return ResponseEntity.ok(ApprenticeshipCheckResponse.builder()
                .doesExist(doesExist)
                .apprenticeProfileId(apprenticeProfileId)
                .mentorProfileId(mentorProfileId)
                .build());
    }

    @GetMapping("/mentor/{mentorProfileId}/apprentices")
    public ResponseEntity<List<ApprenticeInfo>> getMentorApprentices(@PathVariable String mentorProfileId) {

        List<Apprentice> apprentices = apprenticeshipService.getApprenticesOfMentor(mentorProfileId);

        List<ApprenticeInfo> response = apprentices.stream()
                .map(a -> ApprenticeInfo.builder()
                        .apprenticeId(a.getApprenticeId())
                        .profileId(a.getProfile().getProfileId())
                        .name(a.getProfile().getName())
                        .username(a.getProfile().getUsername())
                        .since(a.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Get all mentors of an apprentice
    @GetMapping("/apprentice/{apprenticeProfileId}/mentors")
    public ResponseEntity<List<MentorInfo>> getApprenticeMentors(
            @PathVariable String apprenticeProfileId) {

        List<Mentor> mentors = apprenticeshipService.getMentorsOfApprentice(apprenticeProfileId);

        List<MentorInfo> response = mentors.stream()
                .map(m -> MentorInfo.builder()
                        .mentorId(m.getMentorId())
                        .profileId(m.getProfile().getProfileId())
                        .name(m.getProfile().getName())
                        .username(m.getProfile().getUsername())
                        .apprenticeCount(m.getApprenticeCount())
                        .since(m.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

//    // Get apprenticeship statistics
//    @GetMapping("/stats/{profileId}")
//    public ResponseEntity<ApprenticeshipStatsResponse> getStats(
//            @PathVariable String profileId) {
//
//        return ResponseEntity.ok(apprenticeshipService.getApprenticeshipStats(profileId));
//
//        return ResponseEntity.ok(ApprenticeshipCheckResponse.builder()
//                .profileId(profileId)
//                .mentorCount(stats.getMentorCount())
//                .apprenticeCount(stats.getApprenticeCount())
//                .build());
//    }

}
