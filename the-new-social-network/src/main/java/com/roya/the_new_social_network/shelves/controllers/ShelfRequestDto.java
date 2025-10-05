package com.roya.the_new_social_network.shelves.controllers;

import com.roya.the_new_social_network.global.ComponentVisibility;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public record ShelfRequestDto (String category, String banner, String parentShelfId, ComponentVisibility visibility) {
}
