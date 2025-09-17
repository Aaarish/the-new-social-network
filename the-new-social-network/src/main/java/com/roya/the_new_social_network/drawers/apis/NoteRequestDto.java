package com.roya.the_new_social_network.drawers.apis;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public record NoteRequestDto(String title, String content) {
}
