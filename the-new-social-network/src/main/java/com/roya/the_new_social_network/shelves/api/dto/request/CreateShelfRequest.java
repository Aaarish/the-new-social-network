package com.roya.the_new_social_network.shelves.api.dto.request;

import com.roya.the_new_social_network.global.enums.ComponentVisibility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateShelfRequest {
    @NotBlank(message = "Profile ID is required")
    private String profileId;

    private String projectId; // null for personal shelves

    private String parentShelfId; // null for top-level shelves

    @Size(max = 100, message = "Category cannot exceed 100 characters")
    private String category;

    @Size(max = 500, message = "Banner URL cannot exceed 500 characters")
    private String banner;

    @lombok.Builder.Default
    private ComponentVisibility visibility = ComponentVisibility.PUBLIC;

}
