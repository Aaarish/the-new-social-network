package com.roya.the_new_social_network.drawers.api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteResponse {
    private String id;
    private String title;
    private String content;
    private String drawerId;
}
