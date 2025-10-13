package com.roya.the_new_social_network.shelves.sections;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public record SectionRequestDto(String heading, String content, String image, String url) {
}
