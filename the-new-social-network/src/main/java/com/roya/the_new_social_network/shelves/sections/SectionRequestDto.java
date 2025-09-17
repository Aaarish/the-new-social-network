package com.roya.the_new_social_network.shelves.sections;

import com.roya.the_new_social_network.global.ComponentVisibility;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public record SectionRequestDto(String heading, String content, String image, String url) {
}
