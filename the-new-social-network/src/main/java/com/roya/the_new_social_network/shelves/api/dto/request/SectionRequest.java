package com.roya.the_new_social_network.shelves.api.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionRequest {
    @Size(max = 200, message = "Heading cannot exceed 200 characters")
    private String heading;

    @Size(max = 10000, message = "Content cannot exceed 10000 characters")
    private String content;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    private String image;

    @Size(max = 500, message = "URL cannot exceed 500 characters")
    private String url;

}
